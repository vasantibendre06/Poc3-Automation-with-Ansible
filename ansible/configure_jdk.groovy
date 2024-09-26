import jenkins.model.*
import hudson.model.*
import hudson.tools.*
// Get the Jenkins instance
def instance = Jenkins.getInstance()
// JDK installation
def jdk = new JDK("JDK 17", "/usr/lib/jvm/java-17-openjdk-amd64")
// Get the JDK descriptor
def jdkDescriptor = instance.getDescriptorByType(JDK.DescriptorImpl.class)
// Get existing JDK installations
def jdkInstallations = jdkDescriptor.getInstallations()
// Add new JDK to installations array
jdkInstallations += jdk
// Update the installations
jdkDescriptor.setInstallations(jdkInstallations as JDK[])
// SonarQube Scanner installation
def sonarScanner = new InstallSourceProperty([
    new DownloaderInstallSource("https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/4.6.2.2472/sonar-scanner-cli-4.6.2.2472-linux.zip", "sonar-scanner", "SonarQube Scanner")
])
def sonarScannerInstallation = new ToolLocation("SonarQube Scanner", "/opt/sonar-scanner/bin", sonarScanner)
// Get the SonarQube Scanner descriptor
def sonarScannerDescriptor = instance.getDescriptorByType(SonarScanner.DescriptorImpl.class)
// Get existing SonarQube Scanner installations
def sonarScannerInstallations = sonarScannerDescriptor.getInstallations()
// Add new SonarQube Scanner to installations array
sonarScannerInstallations += sonarScannerInstallation
// Update the installations
sonarScannerDescriptor.setInstallations(sonarScannerInstallations as SonarScanner[])
// Save the configuration
instance.save()
println "JDK 17 and SonarQube Scanner have been configured successfully."
