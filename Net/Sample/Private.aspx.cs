using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Asp
{
    public partial class Private : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie myCookie = new HttpCookie("User");
            myCookie = Request.Cookies["User"];
            if (myCookie != null)
                WelcomeBackMessage.Text = "Welcome back, " + myCookie.Value + "!";
            else
                Response.Redirect("Index.aspx");
        }
    }
}