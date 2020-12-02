import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class Main {
  public static void main(String[] args) {
    PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
    Pipeline p = Pipeline.create(options);
    p.apply("Read from BigQuery", BigQueryIO.readTableRows()
        .fromQuery("SELECT * FROM [bigquery-public-data.chicago_taxi_trips.taxi_trips] WHERE trip_seconds > 0 limit 5000"));
    p.run().waitUntilFinish();
  }
}
