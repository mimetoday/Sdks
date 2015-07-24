<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="Asp.Index" MasterPageFile="~/Layout.Master" %>
<asp:Content ID="Content1" ContentPlaceHolderId="ContentPlaceHolder" runat="server">
    <div class="main-content">
        <iframe id="frame" src="https://localhost/iframe/loginframe.html?entrypoint=sample&usesession=true&register=true&manage=true" 
            style="height: 600px;width:70%;border: none;margin-left: 100px;"></iframe>
    </div>
    <script>
        $(document).ready(function () {
            window.onmessage = function (e) {
                var success = false;
                var userName = null;
                var data = { Status: e.data.status, Token: e.data.token };
                switch (e.data.source) {
                    case 'login':
                        $('<form action="LoginHandler.ashx" method="POST"/>')
                        .append($('<input type="hidden" name="Token" value="' + data.Token + '">'))
                        .append($('<input type="hidden" name="Status" value="' + data.Status + '">'))
                        .appendTo($(document.body)) 
                        .submit();
                        //$.post("/LoginHandler.ashx", data);
                        break;
                }
            }
        });
    </script>
</asp:Content>