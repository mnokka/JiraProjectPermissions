// Change listed projects Persmisison scheme to hardcoded ID value (check from Jira UI)
// mika.nokka1@gmail.com 1.6.2021
// can be executed in Script Runner script console

import com.atlassian.jira.component.ComponentAccessor
import org.apache.log4j.Logger
import org.apache.log4j.Level
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.permission.PermissionSchemeService
import com.atlassian.jira.permission.PermissionScheme
import com.atlassian.jira.permission.PermissionSchemeManager


// Get the Project Manager
def projectManager = ComponentAccessor.getProjectManager()
def pss = ComponentAccessor.getComponent(PermissionSchemeService)


// ************** CONFIGURATIONS ACCORDING THE JIRA **************
// list of project to be having new permission scheme
def thelist=["Test Permission 1","Test Permission Second Project","Test Permissions Task Project"]
// Test permission scheme ID fromU I
def permmissionid=10201
// ************** END OF CONFIGURATIONS **************

Logger mylogger = Logger.getLogger("permissions")
mylogger.setLevel(Level.DEBUG)
mylogger.info("---------- permissions started ------------------------------")


ApplicationUser loggedinuser=ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
mylogger.debug("loggedinuser:$loggedinuser")


def id
def projobject

thelist.each {
	projobject = projectManager.getProjectObjByName(it)
	id=projobject.getId()
	mylogger.debug("Project: $it id:$id")
	// might be usefull to do the validation checking first in case of problems
	pss.assignPermissionSchemeToProject(loggedinuser, permmissionid, id)
}



mylogger.info("---------- permissions ended ------------------------------")