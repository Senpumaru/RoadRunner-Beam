package com.roadrunner;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

public class BeamTest {
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.create());

        pipeline.apply(Create.of("Hello", "Beam", "Test"))
                .apply(ParDo.of(new DoFn<String, String>() {
                    @ProcessElement
                    public void processElement(@Element String element, OutputReceiver<String> out) {
                        out.output(element.toUpperCase());
                    }
                }))
                .apply(ParDo.of(new DoFn<String, Void>() {
                    @ProcessElement
                    public void processElement(@Element String element) {
                        System.out.println(element);
                    }
                }));

        pipeline.run().waitUntilFinish();
    }
}