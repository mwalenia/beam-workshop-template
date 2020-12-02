# Beam workshops template project

This repo contains the template for Apache Beam workshops task.

Main branch is an empty pipeline.

Extensions of the pipeline are in branches `read-from-bigquery` and `custom-data-transformation`

In order to run the pipeline on Dataflow use the command:

```
./gradlew execute -DmainClass=Main \
-Dexec.args="--runner=DataflowRunner --tempLocation=gs://<temp-location>/ --region=us-central1" \
-Pdataflow-runner --info
```

