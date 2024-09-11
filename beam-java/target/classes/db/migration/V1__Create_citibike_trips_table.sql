-- V1__Create_citibike_trips_table.sql
CREATE TABLE citibike_trips (
    ride_id VARCHAR(255),
    rideable_type VARCHAR(50),
    started_at TIMESTAMP,
    ended_at TIMESTAMP,
    start_station_name VARCHAR(255),
    start_station_id VARCHAR(50),
    end_station_name VARCHAR(255),
    end_station_id VARCHAR(50),
    start_lat DOUBLE PRECISION,
    start_lng DOUBLE PRECISION,
    end_lat DOUBLE PRECISION,
    end_lng DOUBLE PRECISION,
    member_casual VARCHAR(20)
);

-- Create indexes for better query performance
CREATE INDEX idx_started_at ON citibike_trips(started_at);
CREATE INDEX idx_ended_at ON citibike_trips(ended_at);
CREATE INDEX idx_start_station_id ON citibike_trips(start_station_id);
CREATE INDEX idx_end_station_id ON citibike_trips(end_station_id);