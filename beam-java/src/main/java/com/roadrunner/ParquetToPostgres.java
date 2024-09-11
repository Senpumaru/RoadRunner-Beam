package com.roadrunner;

import org.apache.beam.runners.spark.SparkPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.parquet.ParquetIO;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.runners.spark.SparkRunner;
import org.apache.avro.generic.GenericRecord;
import org.apache.beam.sdk.values.PCollection;

public class ParquetToPostgres {
    public static void main(String[] args) {
        // Set up Beam pipeline options
        SparkPipelineOptions options = PipelineOptionsFactory.as(SparkPipelineOptions.class);
        options.setRunner(SparkRunner.class);
        options.setSparkMaster("spark://spark-master:7077");
        options.setFilesToStage(null);

        // Create the pipeline
        Pipeline pipeline = Pipeline.create(options);

        PCollection<GenericRecord> records = pipeline.apply(
            "Read Parquet", 
            ParquetIO.read(GenericRecord.class).from("./data/nyc_taxi_data/yellow_tripdata_2023-01.parquet")
        );

        records.apply(
            "Write to PostgreSQL",
            JdbcIO.<GenericRecord>write()
                .withDataSourceConfiguration(JdbcIO.DataSourceConfiguration.create(
                    "org.postgresql.Driver", "jdbc:postgresql://postgres:5432/roadrunner")
                    .withUsername("roadrunner")
                    .withPassword("roadrunner_password"))
                .withStatement("INSERT INTO citibike_trips (ride_id, rideable_type, started_at, ended_at, start_station_name, start_station_id, end_station_name, end_station_id, start_lat, start_lng, end_lat, end_lng, member_casual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                .withPreparedStatementSetter((element, statement) -> {
                    statement.setString(1, element.get("ride_id").toString());
                    statement.setString(2, element.get("rideable_type").toString());
                    statement.setTimestamp(3, new java.sql.Timestamp((Long) element.get("started_at")));
                    statement.setTimestamp(4, new java.sql.Timestamp((Long) element.get("ended_at")));
                    statement.setString(5, element.get("start_station_name").toString());
                    statement.setString(6, element.get("start_station_id").toString());
                    statement.setString(7, element.get("end_station_name").toString());
                    statement.setString(8, element.get("end_station_id").toString());
                    statement.setDouble(9, (Double) element.get("start_lat"));
                    statement.setDouble(10, (Double) element.get("start_lng"));
                    statement.setDouble(11, (Double) element.get("end_lat"));
                    statement.setDouble(12, (Double) element.get("end_lng"));
                    statement.setString(13, element.get("member_casual").toString());
                })
        );

        pipeline.run().waitUntilFinish();
    }
}