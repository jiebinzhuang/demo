package com.zhuangjb.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SnoopServlet extends HttpServlet {

	private static final long serialVersionUID = -2532129078676932471L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>Snoop Servlet</TITLE></HEAD><BODY BGCOLOR=\"#FFFFEE\" style=\"text-align:center;\"><div style=\"margin:0px auto;width:800px;\">");
		out.println("<h1>Snoop Servlet - Request/Client Information</h1>");
		out.println("<h2>Requested URL:</h2>");
		out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
		out.println((new StringBuilder()).append("<tr><td>").append(escapeChar(request.getRequestURL().toString()))
				.append("</td></tr></table><BR><BR>").toString());
		out.println("<h2>Servlet Name:</h2>");
		out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
		out.println((new StringBuilder()).append("<tr><td>").append(escapeChar(getServletConfig().getServletName()))
				.append("</td></tr></table><BR><BR>").toString());
		Enumeration<?> vEnum = getServletConfig().getInitParameterNames();
		if(vEnum != null && vEnum.hasMoreElements()) {
			boolean first = true;
			String param;
			for(; vEnum.hasMoreElements(); out.println((new StringBuilder()).append("<tr><td>")
					.append(escapeChar(param)).append("</td><td>").append(escapeChar(getInitParameter(param)))
					.append("</td></tr>").toString())) {
				if(first) {
					out.println("<h2>Servlet Initialization Parameters</h2>");
					out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
					first = false;
				}
				param = (String) vEnum.nextElement();
			}

			out.println("</table><BR><BR>");
		}
		vEnum = getServletConfig().getServletContext().getInitParameterNames();
		if(vEnum != null && vEnum.hasMoreElements()) {
			boolean first = true;
			String param;
			for(; vEnum.hasMoreElements(); out.println((new StringBuilder()).append("<tr><td>")
					.append(escapeChar(param)).append("</td><td>")
					.append(escapeChar(getServletConfig().getServletContext().getInitParameter(param)))
					.append("</td></tr>").toString())) {
				if(first) {
					out.println("<h2>Servlet Context Initialization Parameters</h2>");
					out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
					first = false;
				}
				param = (String) vEnum.nextElement();
			}

			out.println("</table><BR><BR>");
		}
		out.println("<h2>Request Information:</h2>");
		out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
		print(out, "Request method", request.getMethod());
		print(out, "Request URI", request.getRequestURI());
		print(out, "Request protocol", request.getProtocol());
		print(out, "Servlet path", request.getServletPath());
		print(out, "Path info", request.getPathInfo());
		print(out, "Path translated", request.getPathTranslated());
		print(out, "Character encoding", request.getCharacterEncoding());
		print(out, "Query string", request.getQueryString());
		print(out, "Content length", request.getContentLength());
		print(out, "Content type", request.getContentType());
		print(out, "Server name", request.getServerName());
		print(out, "Server port", request.getServerPort());
		print(out, "Remote user", request.getRemoteUser());
		print(out, "Remote address", request.getRemoteAddr());
		print(out, "Remote host", request.getRemoteHost());
		print(out, "Remote port", request.getRemotePort());
		print(out, "Local address", request.getLocalAddr());
		print(out, "Local host", request.getLocalName());
		print(out, "Local port", request.getLocalPort());
		print(out, "Authorization scheme", request.getAuthType());
		if(request.getLocale() != null)
			print(out, "Preferred Client Locale", request.getLocale().toString());
		else
			print(out, "Preferred Client Locale", "none");
		for(Enumeration<?> ee = request.getLocales(); ee.hasMoreElements();) {
			Locale cLocale = (Locale) ee.nextElement();
			if(cLocale != null)
				print(out, "All Client Locales", cLocale.toString());
			else
				print(out, "All Client Locales", "none");
		}

		print(out, "Context Path", escapeChar(request.getContextPath()));
		if(request.getUserPrincipal() != null)
			print(out, "User Principal", escapeChar(request.getUserPrincipal().getName()));
		else
			print(out, "User Principal", "none");
		out.println("</table><BR><BR>");
		Enumeration<?> e = request.getHeaderNames();
		if(e.hasMoreElements()) {
			out.println("<h2>Request headers:</h2>");
			out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
			String name;
			for(; e.hasMoreElements(); out.println((new StringBuilder()).append("<tr><td>").append(escapeChar(name))
					.append("</td><td>").append(escapeChar(request.getHeader(name))).append("</td></tr>").toString()))
				name = (String) e.nextElement();

			out.println("</table><BR><BR>");
		}
		e = request.getParameterNames();
		if(e.hasMoreElements()) {
			out.println("<h2>Servlet parameters (Single Value style):</h2>");
			out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
			String name;
			for(; e.hasMoreElements(); out
					.println((new StringBuilder()).append("<tr><td>").append(escapeChar(name)).append("</td><td>")
							.append(escapeChar(request.getParameter(name))).append("</td></tr>").toString()))
				name = (String) e.nextElement();

			out.println("</table><BR><BR>");
		}
		e = request.getParameterNames();
		if(e.hasMoreElements()) {
			out.println("<h2>Servlet parameters (Multiple Value style):</h2>");
			out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
			do {
				if(!e.hasMoreElements())
					break;
				String name = (String) e.nextElement();
				String vals[] = request.getParameterValues(name);
				if(vals != null) {
					out.print((new StringBuilder()).append("<tr><td>").append(escapeChar(name)).append("</td><td>")
							.toString());
					out.print(escapeChar(vals[0]));
					for(int i = 1; i < vals.length; i++)
						out.print((new StringBuilder()).append(", ").append(escapeChar(vals[i])).toString());

					out.println("</td></tr>");
				}
			} while(true);
			out.println("</table><BR><BR>");
		}
		String cipherSuite = (String) request.getAttribute("javax.net.ssl.cipher_suite");
		if(cipherSuite != null) {
			X509Certificate certChain[] = (X509Certificate[]) request.getAttribute("javax.net.ssl.peer_certificates");
			out.println("<h2>HTTPS Information:</h2>");
			out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
			out.println((new StringBuilder()).append("<tr><td>Cipher Suite</td><td>").append(escapeChar(cipherSuite))
					.append("</td></tr>").toString());
			if(certChain != null) {
				for(int i = 0; i < certChain.length; i++)
					out.println((new StringBuilder()).append("client cert chain [").append(i).append("] = ")
							.append(escapeChar(certChain[i].toString())).toString());

			}
			out.println("</table><BR><BR>");
		}
		Cookie cookies[] = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			out.println("<H2>Client cookies</H2>");
			out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
			for(int i = 0; i < cookies.length; i++)
				out.println((new StringBuilder()).append("<tr><td>").append(escapeChar(cookies[i].getName()))
						.append("</td><td>").append(escapeChar(cookies[i].getValue())).append("</td></tr>").toString());

			out.println("</table><BR><BR>");
		}
		e = request.getAttributeNames();
		if(e.hasMoreElements()) {
			out.println("<h2>Request attributes:</h2>");
			out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
			String name;
			for(; e.hasMoreElements(); out.println((new StringBuilder()).append("<tr><td>").append(escapeChar(name))
					.append("</td><td>").append(escapeChar(request.getAttribute(name).toString())).append("</td></tr>")
					.toString()))
				name = (String) e.nextElement();

			out.println("</table><BR><BR>");
		}
		e = getServletContext().getAttributeNames();
		if(e.hasMoreElements()) {
			out.println("<h2>ServletContext attributes:</h2>");
			out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
			String name;
			for(; e.hasMoreElements(); out.println((new StringBuilder()).append("<tr><td>").append(escapeChar(name))
					.append("</td><td>").append(escapeChar(getServletContext().getAttribute(name).toString()))
					.append("</td></tr>").toString()))
				name = (String) e.nextElement();

			out.println("</table><BR><BR>");
		}
		HttpSession session = request.getSession(false);
		if(session != null) {
			out.println("<h2>Session information:</h2>");
			out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
			print(out, "Session ID", session.getId());
			print(out, "Last accessed time", (new Date(session.getLastAccessedTime())).toString());
			print(out, "Creation time", (new Date(session.getCreationTime())).toString());
			String mechanism = "unknown";
			if(request.isRequestedSessionIdFromCookie())
				mechanism = "cookie";
			else if(request.isRequestedSessionIdFromURL())
				mechanism = "url-encoding";
			print(out, "Session-tracking mechanism", mechanism);
			out.println("</table><BR><BR>");
			Enumeration<?> vals = session.getAttributeNames();
			if(vals.hasMoreElements()) {
				out.println("<h2>Session values</h2>");
				out.println("<TABLE Border=\"2\" WIDTH=\"100%\" BGCOLOR=\"#DDDDFF\">");
				String name;
				for(; vals.hasMoreElements(); out.println((new StringBuilder()).append("<tr><td>")
						.append(escapeChar(name)).append("</td><td>")
						.append(escapeChar(session.getAttribute(name).toString())).append("</td></tr>").toString()))
					name = (String) vals.nextElement();

				out.println("</table><BR><BR>");
			}
		}
		out.println("</div></body></html>");
	}

	private void print(PrintWriter out, String name, String value) {
		out.println((new StringBuilder()).append("<tr><td>").append(name).append("</td><td>")
				.append(value != null ? escapeChar(value) : "&lt;none&gt;").append("</td></tr>").toString());
	}

	private void print(PrintWriter out, String name, int value) {
		out.print((new StringBuilder()).append("<tr><td>").append(name).append("</td><td>").toString());
		if(value == -1)
			out.print("&lt;none&gt;");
		else
			out.print(value);
		out.println("</td></tr>");
	}

	private String escapeChar(String str) {
		char src[] = str.toCharArray();
		int len = src.length;
		for(int i = 0; i < src.length; i++)
			switch(src[i]){
			case 60: // '<'
				len += 3;
				break;

			case 62: // '>'
				len += 3;
				break;

			case 38: // '&'
				len += 4;
				break;
			}

		char ret[] = new char[len];
		int j = 0;
		for(int i = 0; i < src.length; i++)
			switch(src[i]){
			case 60: // '<'
				ret[j++] = '&';
				ret[j++] = 'l';
				ret[j++] = 't';
				ret[j++] = ';';
				break;

			case 62: // '>'
				ret[j++] = '&';
				ret[j++] = 'g';
				ret[j++] = 't';
				ret[j++] = ';';
				break;

			case 38: // '&'
				ret[j++] = '&';
				ret[j++] = 'a';
				ret[j++] = 'm';
				ret[j++] = 'p';
				ret[j++] = ';';
				break;

			default:
				ret[j++] = src[i];
				break;
			}

		return new String(ret);
	}
}
