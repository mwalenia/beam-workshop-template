import com.google.api.services.bigquery.model.TableRow;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

public class Main {
  public static void main(String[] args) {
    PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
    Pipeline p = Pipeline.create(options);
    p.apply("Read from BigQuery", BigQueryIO.readTableRows()
        .fromQuery("SELECT * FROM [bigquery-public-data.chicago_taxi_trips.taxi_trips] WHERE trip_seconds > 0 limit 5000"))
        .apply("Map to objects", ParDo.of(new DoFn<TableRow, CompanyFare>() {

          @ProcessElement
          public void process(ProcessContext context) {
            CompanyFare fare = new CompanyFare();
            TableRow row = context.element();
            fare.setCompanyName((String) row.get("company"));
            fare.setTip((Double) row.get("tips"));
            fare.setTotal((Double) row.get("total"));
            context.output(fare);
          }
        }));
    p.run().waitUntilFinish();
  }
}
