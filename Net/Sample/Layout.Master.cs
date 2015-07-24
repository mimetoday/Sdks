using Mime.Sdk;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Asp
{
    public partial class Layout : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["User"] == null)
            {
                LogoutDiv.Visible = false;
            }
            else
            {
                LogoutDiv.Visible = true;
            }
        }

        protected void Logout_Click(object sender, EventArgs e)
        {
            string serviceUrl = System.Configuration.ConfigurationManager.AppSettings["serviceUrl"];
            string yourHTMLstring = serviceUrl + "/iframe/logoutframe.html";
            ContentIframe.Attributes["src"] = yourHTMLstring;
            if (Request.Cookies["User"] != null)
            {
                //Remove cookie
                HttpCookie myCookie = new HttpCookie("User");
                myCookie.Expires = DateTime.Now.AddDays(-1d);
                Response.Cookies.Add(myCookie);
            }
            Response.Redirect("Index.aspx");
        }
    }
}