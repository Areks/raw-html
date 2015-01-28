package org.apache.nutch.parse;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.metadata.Metadata;
import org.apache.nutch.parse.HTMLMetaTags;
import org.apache.nutch.parse.ParseFilter;
import org.apache.nutch.parse.Parse;
import org.apache.nutch.protocol.Content;
import org.w3c.dom.DocumentFragment;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author anupam
 */
public class NoTrimHTMLParseFilter implements ParseFilter {
    private Configuration conf;

    @Override
    public Configuration getConf() {
        return conf;
    }

    @Override
    public void setConf(Configuration arg0) {
        conf = arg0;
    }

    @Override
    public Parse filter(Content content, Parse parseResult, HTMLMetaTags metaTags, DocumentFragment doc) {
        try {
            Metadata metadata = parseResult.get(content.getUrl()).getData().getParseMeta();
            byte[] rawContent = content.getContent();
            String str = new String(rawContent, "UTF-8");
            metadata.add("rawcontent", str);
            return parseResult;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NoTrimHTMLParseFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}