import jenkins.model.Jenkins;

pm = Jenkins.instance.pluginManager
uc = Jenkins.instance.updateCenter

// Calls Plugin Catalog and Download All the Information that require
pm.doCheckUpdatesServer()

// List of PlugIn with Dependencies
["github", "workflow-aggregator", "docker-build-publish", "npm-yarn-wrapper-steps", "pipeline-stage-view", "ws-cleanup", "role-strategy"].each {
  if (! pm.getPlugin(it)) {
    deployment = uc.getPlugin(it).deploy(true)
    deployment.get()
  }
}

// Restart Jenkins after installing plugins (optional)
Jenkins.instance.restart()