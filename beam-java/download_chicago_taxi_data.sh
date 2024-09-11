#!/bin/bash

# Create a directory for the data
mkdir -p chicago_taxi_data
cd chicago_taxi_data

# Download a sample of the data (adjust the size as needed)
wget -O chicago_taxi_trips.parquet "https://data.cityofchicago.org/api/views/wrvz-psew/rows.parquet?accessType=DOWNLOAD&api_foundry=true&limit=1000000"

echo "Download complete. File saved as chicago_taxi_trips.parquet"