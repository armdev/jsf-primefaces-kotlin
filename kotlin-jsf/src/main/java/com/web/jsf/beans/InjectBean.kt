/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.jsf.beans

import javax.inject.Named
import java.io.Serializable
import java.util.*
import javax.annotation.PostConstruct
import javax.faces.context.ExternalContext
import javax.faces.context.FacesContext
import javax.faces.bean.ManagedBean
import javax.faces.bean.SessionScoped
import javax.faces.application.FacesMessage
import javax.faces.bean.ViewScoped

/**

 * @author armenar
 */
@ManagedBean(name = "injectBean")
@ViewScoped
class InjectBean : Serializable {

    private var context: FacesContext? = null
    private var externalContext: ExternalContext? = null
    var saveManager: SaveBean? = null
    var userModel: UserModel? = null


    @PostConstruct
    fun init() {
        context = FacesContext.getCurrentInstance()
        externalContext = context!!.externalContext
        saveManager = context!!.application.evaluateExpressionGet(
                context, "#{saveBean}", SaveBean::class.java)
        userModel = UserModel()
    }

    fun doSave(): String {
        saveManager!!.finalSave(userModel!!)
        return "index"
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
