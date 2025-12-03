import pyodbc

# SQL connection
conn = pyodbc.connect(
    "Driver={ODBC Driver 17 for SQL Server};"
    "Server=DESKTOP-51HPISO;"
    "Database=DepiProject_cr7;"
    "Trusted_Connection=yes;"
)
cursor = conn.cursor()

print("🚀 Running ETL from ODS → DW ...")

# 1) Read ODS records not loaded
cursor.execute("""
SELECT * FROM ODS_WeatherReadings
WHERE IsLoadedToDW = 0
""")

rows = cursor.fetchall()

for row in rows:
    timestamp = row.Timestamp
    device_id = row.DeviceID
    device_type = row.DeviceType
    governorate = row.Governorate
    city = row.City
    zone = row.Zone

    # ------------------------------
    # DIM DATE
    # ------------------------------
    date_key = int(timestamp.strftime("%Y%m%d%H"))  # unique per hour
    
    cursor.execute("SELECT DateKey FROM DimDate WHERE DateKey=?", date_key)
    found = cursor.fetchone()

    if not found:
        cursor.execute("""
            INSERT INTO DimDate (DateKey, FullDate, Day, Month, MonthName, Year, Hour, Minute)
            VALUES (?,?,?,?,?,?,?,?)
        """,
        date_key, timestamp, timestamp.day, timestamp.month,
        timestamp.strftime("%B"), timestamp.year,
        timestamp.hour, timestamp.minute
        )
        conn.commit()

    # ------------------------------
    # DIM DEVICE
    # ------------------------------
    cursor.execute("""
        SELECT DeviceKey FROM DimDevice
        WHERE DeviceID=? AND DeviceType=?
    """, device_id, device_type)
    device_row = cursor.fetchone()

    if device_row:
        device_key = device_row[0]
    else:
        cursor.execute("""
            INSERT INTO DimDevice (DeviceID, DeviceType)
            VALUES (?,?)
        """, device_id, device_type)
        conn.commit()
        device_key = cursor.lastrowid

    # ------------------------------
    # DIM LOCATION
    # ------------------------------
    cursor.execute("""
        SELECT LocationKey FROM DimLocation
        WHERE Governorate=? AND City=? AND Zone=?
    """, governorate, city, zone)
    loc_row = cursor.fetchone()

    if loc_row:
        location_key = loc_row[0]
    else:
        cursor.execute("""
            INSERT INTO DimLocation (Governorate, City, Zone)
            VALUES (?,?,?)
        """, governorate, city, zone)
        conn.commit()
        location_key = cursor.lastrowid

    # ------------------------------
    # INSERT FACT
    # ------------------------------
    cursor.execute("""
        INSERT INTO FactWeatherReadings (
            DateKey, DeviceKey, LocationKey,
            Temperature_C, Humidity_pct, WindSpeed_kmh,
            WindDirection, Rainfall_mm, CloudCoverage_pct,
            UV_Index, Pressure_hPa, Battery_pct,
            AlertLevel, AlertType, Advisory, IsAnomaly, AnomalyReason
        ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
    """,
        date_key, device_key, location_key,
        row.Temperature_C, row.Humidity_pct, row.WindSpeed_kmh,
        row.WindDirection, row.Rainfall_mm, row.CloudCoverage_pct,
        row.UV_Index, row.Pressure_hPa, row.Battery_pct,
        row.AlertLevel, row.AlertType, row.Advisory,
        row.IsAnomaly, row.AnomalyReason
    )
    conn.commit()

    # Mark record as loaded
    cursor.execute("""
        UPDATE ODS_WeatherReadings
        SET IsLoadedToDW = 1
        WHERE Timestamp=? AND DeviceID=?
    """, timestamp, device_id)
    conn.commit()

print("🎉 ETL Completed and DW updated!")
