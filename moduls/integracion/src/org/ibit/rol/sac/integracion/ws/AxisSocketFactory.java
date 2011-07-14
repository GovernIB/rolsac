package org.ibit.rol.sac.integracion.ws;

import java.util.Hashtable;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

import org.apache.axis.components.net.DefaultSocketFactory;
import org.apache.axis.components.net.BooleanHolder;
import org.apache.axis.components.net.TransportClientProperties;
import org.apache.axis.components.net.TransportClientPropertiesFactory;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.encoding.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Se debe fijar "axis.socketFactory" con el nombre de esta classe
 * para que sea usada.
 */
public class AxisSocketFactory extends DefaultSocketFactory {

    protected static Log log = LogFactory.getLog(AxisSocketFactory.class);

    public AxisSocketFactory(Hashtable attributes) {
        super(attributes);
    }

    public Socket create(String host, int port, StringBuffer otherHeaders, BooleanHolder useFullURL)
            throws Exception {
        log.debug("Create socket " + host + ":" + port);

        TransportClientProperties tcp = TransportClientPropertiesFactory.create("http");
        boolean hostInNonProxyList = isHostInNonProxyList(host, tcp.getNonProxyHosts());

        if (tcp.getProxyUser().length() != 0) {
            StringBuffer tmpBuf = new StringBuffer();

            tmpBuf.append(tcp.getProxyUser())
                    .append(":")
                    .append(tcp.getProxyPassword());
            otherHeaders.append(HTTPConstants.HEADER_PROXY_AUTHORIZATION)
                    .append(": Basic ")
                    .append(Base64.encode(tmpBuf.toString().getBytes()))
                    .append("\r\n");
        }

        if (port == -1) {
            port = 80;
        }

        Socket sock = new Socket();
        SocketAddress address;
        if ((tcp.getProxyHost().length() == 0) ||
                (tcp.getProxyPort().length() == 0) ||
                hostInNonProxyList) {
            address = new InetSocketAddress(host, port);
        } else {
            address = new InetSocketAddress(tcp.getProxyHost(), Integer.parseInt(tcp.getProxyPort()));
            useFullURL.value = true;
        }

        sock.connect(address, 10000);

        return sock;
    }
}
