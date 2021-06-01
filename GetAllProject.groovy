// log all Jira projects to a single list
// mika.nokka1@gmail.com 1.6.2021
// can be executed in Script Runner script console

import com.atlassian.jira.component.ComponentAccessor
import org.apache.log4j.Logger
import org.apache.log4j.Level

// Get the Project Manager
def projectManager = ComponentAccessor.getProjectManager()

Logger mylogger = Logger.getLogger("permissions")
mylogger.setLevel(Level.DEBUG)
mylogger.info("---------- LOG_SLA_TIMES started ------------------------------")


def projects = projectManager.getProjectObjects()

def listed=[]
projects.each {
	//mylogger.debug("$it.name")
	listed.add(it.name)
}

mylogger.debug("${listed}")
mylogger.info("---------- LOG_SLA_TIMES ended ------------------------------")
