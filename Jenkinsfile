@Library('jenkins-library') _

def pipeline = new org.android.ShareFeature(
  steps: this,
  agentImage: 'build-tools/android-build-box:jdk17',
  gitUpdateSubmodule: true,
  sonarProjectKey: "sora:android-foundation",
  sonarProjectName: "android-foundation",
  dojoProductType: "sora-mobile",
  disableDojo: true,
  sonar: false
)

pipeline.runPipeline()