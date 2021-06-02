// Change listed projects Persmisison scheme to hardcoded ID value (check from Jira UI)
// mika.nokka1@gmail.com 1.6.2021
// can be executed in Script Runner script console

import com.atlassian.jira.component.ComponentAccessor
import org.apache.log4j.Logger
import org.apache.log4j.Level
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.permission.PermissionSchemeService
import com.atlassian.jira.permission.PermissionSchemeManager


// Get the Project Manager
def projectManager = ComponentAccessor.getProjectManager()
def pss = ComponentAccessor.getComponent(PermissionSchemeService)

Logger mylogger = Logger.getLogger("permissions")
mylogger.setLevel(Level.DEBUG)
mylogger.info("---------- LOG_SLA_TIMES started ------------------------------")


ApplicationUser loggedinuser=ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
mylogger.debug("loggedinuser:$loggedinuser")
def iduser = loggedinuser.getKey()
mylogger.debug("iduser:$iduser")
def projects = projectManager.getProjectObjects()

def listed=[]
def id
def permscheme
projects.each {
	id = it.getId()
	mylogger.debug("$it.name   id:$id")
	permscheme=pss.getSchemeAssignedToProject( loggedinuser, id)
	mylogger.debug("permscheme:$permscheme")
	
	listed.add(it.name)
}

mylogger.debug("${listed}")
mylogger.info("---------- LOG_SLA_TIMES ended ------------------------------")