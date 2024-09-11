#!/bin/bash

# Create a directory for the data
mkdir -p nyc_taxi_data
cd nyc_taxi_data

# Download a month of data (adjust the year and month as needed)
wget https://d37ci6vzurychx.cloudfront.net/trip-data/yellow_tripdata_2023-01.parquet

echo "Download complete. File saved as yellow_tripdata_2023-01.parquet"