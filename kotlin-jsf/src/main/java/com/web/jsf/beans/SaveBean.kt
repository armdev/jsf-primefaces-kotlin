package com.web.jsf.beans


import java.io.IOException
import java.io.InputStream
import java.io.Serializable

import javax.annotation.PostConstruct
import javax.faces.application.FacesMessage
import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.bean.SessionScoped
import javax.faces.context.ExternalContext
import javax.faces.context.FacesContext
import javax.servlet.http.Part
import org.apache.commons.codec.binary.Base64
import java.util.*

@ManagedBean(name = "saveBean")
@SessionScoped
class SaveBean : Serializable {

    private var context: FacesContext? = null
    private var externalContext: ExternalContext? = null
    private var userList: MutableList<UserModel> = ArrayList()

    @PostConstruct
    fun init() {
        context = FacesContext.getCurrentInstance()
        externalContext = context!!.externalContext
    }


    fun finalSave(userModel: UserModel): Unit {
        println("Starting final save");
        println("This is email " + userModel.email)
        userList.add(userModel)
    }
    
    fun clearData():Unit{
        userList.clear()
    }


    fun getUserList(): List<UserModel> {
        return userList
    }

    fun setUserList(userList: MutableList<UserModel>) {
        this.userList = userList
    }


    fun addMessage(message: FacesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, message)
    }

    private fun facesError(message: String) {
        FacesContext.getCurrentInstance().addMessage(null,
                                                     FacesMessage(FacesMessage.SEVERITY_ERROR, message, null))
    }

    companion object {
        private val serialVersionUID = 1L
    }

}
