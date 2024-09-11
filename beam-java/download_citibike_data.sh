#!/bin/bash

# Create a directory for the data
mkdir -p citibike_data
cd citibike_data

# Download the Parquet files for 2022
for month in {01..12}
do
    wget https://s3.amazonaws.com/tripdata/2022$month-citibike-tripdata.parquet
done

# Note: We don't need to combine Parquet files as Beam can read multiple files