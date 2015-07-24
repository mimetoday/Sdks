using Mime.Sdk;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;

namespace Asp
{
    /// <summary>
    /// Summary description for LoginHandler
    /// </summary>
    public class LoginHandler : IHttpHandler
    {

        public void ProcessRequest(HttpContext context)
        {
            HttpRequest request = context.Request;
            HttpResponse response = context.Response;
            string Status = request.Params["Status"];
            string Token = request.Params["Token"];
            try
            {
                if (Status.Equals("Accept"))
                {
                    string privateKey = System.Configuration.ConfigurationManager.AppSettings["privateKey"];
                    string serviceUrl = System.Configuration.ConfigurationManager.AppSettings["serviceUrl"];
                    string entryPoint = System.Configuration.ConfigurationManager.AppSettings["entryPoint"]; ;
                    LoginManager client = new LoginManager(new Uri(serviceUrl));
                    ProcessLoginOut result = client.ProcessAuthentication(Token, privateKey, entryPoint);
                    switch (result.OperationStatus)
                    {
                        case OperationStatus.Ok:
                            HttpCookie myCookie = new HttpCookie("User");
                            DateTime now = DateTime.Now;
                            myCookie.Value = result.User;
                            myCookie.Expires = now.AddMinutes(5);
                            context.Response.Cookies.Add(myCookie);
                            context.Response.Redirect("Private.aspx");
                            break;
                        case OperationStatus.NotFound:
                            context.Response.Redirect("Index.aspx");
                            break;
                    }
                    context.Response.Redirect("Index.aspx");
                }
            }
            catch (Exception ex)
            {
            }
        }

        public bool IsReusable
        {
            get
            {
                return false;
            }
        }
    }
}