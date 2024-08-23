@Library('jenkins-library') _

def pipeline = new org.android.ShareFeature(
  steps: this,
  agentImage: 'build-tools/android-build-box:jdk17',
  gitUpdateSubmodule: true,
  sonarProjectKey: "sora:android-ui-libraries",
  sonarProjectName: "android-ui-libraries",
  dojoProductType: "sora-mobile"
)

pipeline.runPipeline()