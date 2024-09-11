-- V1__Create_nyc_yellow_taxi_trips_table.sql
CREATE TABLE nyc_yellow_taxi_trips (
    VendorID INTEGER,
    tpep_pickup_datetime TIMESTAMP,
    tpep_dropoff_datetime TIMESTAMP,
    passenger_count FLOAT,
    trip_distance FLOAT,
    RatecodeID FLOAT,
    store_and_fwd_flag VARCHAR(1),
    PULocationID INTEGER,
    DOLocationID INTEGER,
    payment_type INTEGER,
    fare_amount FLOAT,
    extra FLOAT,
    mta_tax FLOAT,
    tip_amount FLOAT,
    tolls_amount FLOAT,
    improvement_surcharge FLOAT,
    total_amount FLOAT,
    congestion_surcharge FLOAT,
    airport_fee FLOAT
);

-- Create indexes for better query performance
CREATE INDEX idx_pickup_datetime ON nyc_yellow_taxi_trips(tpep_pickup_datetime);
CREATE INDEX idx_dropoff_datetime ON nyc_yellow_taxi_trips(tpep_dropoff_datetime);
CREATE INDEX idx_pulocation_id ON nyc_yellow_taxi_trips(PULocationID);
CREATE INDEX idx_dolocation_id ON nyc_yellow_taxi_trips(DOLocationID);