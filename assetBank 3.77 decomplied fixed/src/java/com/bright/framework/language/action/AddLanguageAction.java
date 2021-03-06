/*    */ package com.bright.framework.language.action;
/*    */ 
/*    */ import com.bn2web.common.exception.Bn2Exception;
/*    */ import com.bright.assetbank.language.service.LanguageManager;
/*    */ import com.bright.framework.database.bean.DBTransaction;
/*    */ import com.bright.framework.language.bean.Language;
/*    */ import com.bright.framework.language.constant.LanguageConstants;
/*    */ import com.bright.framework.language.exception.LanguageCodeNotUniqueException;
/*    */ import com.bright.framework.language.exception.LanguageNameNotUniqueException;
/*    */ import com.bright.framework.language.form.LanguageForm;
/*    */ import com.bright.framework.simplelist.bean.ListItem;
/*    */ import com.bright.framework.simplelist.service.ListManager;
/*    */ import com.bright.framework.user.bean.UserProfile;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.struts.action.ActionForm;
/*    */ import org.apache.struts.action.ActionForward;
/*    */ import org.apache.struts.action.ActionMapping;
/*    */ 
/*    */ public class AddLanguageAction extends LanguageAction
/*    */   implements LanguageConstants
/*    */ {
/*    */   public ActionForward execute(ActionMapping a_mapping, ActionForm a_form, HttpServletRequest a_request, HttpServletResponse a_response, DBTransaction a_dbTransaction)
/*    */     throws Bn2Exception
/*    */   {
/* 63 */     LanguageForm form = (LanguageForm)a_form;
/*    */ 
/* 65 */     Language language = form.getLanguage();
/*    */ 
/* 67 */     UserProfile userProfile = UserProfile.getUserProfile(a_request.getSession());
/*    */ 
/* 69 */     if (StringUtils.isEmpty(language.getName()))
/*    */     {
/* 71 */       form.addError(this.m_listManager.getListItem(a_dbTransaction, "languageNameRequired", userProfile.getCurrentLanguage()).getBody());
/* 72 */       return a_mapping.findForward("Failure");
/*    */     }
/* 74 */     if (StringUtils.isEmpty(language.getCode()))
/*    */     {
/* 76 */       form.addError(this.m_listManager.getListItem(a_dbTransaction, "languageCodeRequired", userProfile.getCurrentLanguage()).getBody());
/* 77 */       return a_mapping.findForward("Failure");
/*    */     }
/*    */ 
/* 80 */     language.setSuspended(true);
/* 81 */     language.setDefault(false);
/*    */     try
/*    */     {
/* 85 */       getLanguageManager().addLanguage(a_dbTransaction, language);
/*    */     }
/*    */     catch (LanguageNameNotUniqueException e)
/*    */     {
/* 89 */       form.addError(this.m_listManager.getListItem(a_dbTransaction, "languageNameNotUnique", userProfile.getCurrentLanguage()).getBody());
/* 90 */       return a_mapping.findForward("Failure");
/*    */     }
/*    */     catch (LanguageCodeNotUniqueException e)
/*    */     {
/* 94 */       form.addError(this.m_listManager.getListItem(a_dbTransaction, "languageCodeNotUnique", userProfile.getCurrentLanguage()).getBody());
/* 95 */       return a_mapping.findForward("Failure");
/*    */     }
/*    */ 
/* 98 */     return a_mapping.findForward("Success");
/*    */   }
/*    */ }

/* Location:           C:\Users\mamatha\Desktop\com.zip
 * Qualified Name:     com.bright.framework.language.action.AddLanguageAction
 * JD-Core Version:    0.6.0
 */