package org.apache.nutch.parse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.indexer.IndexingException;
import org.apache.nutch.indexer.IndexingFilter;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.storage.WebPage;

import java.util.Collection;
import java.util.Collections;

/**
 * @author anupam
 */
public class NoTrimHTMLParseFilter implements IndexingFilter {

    private static final Log LOG = LogFactory
            .getLog(NoTrimHTMLParseFilter.class);
    private static final String CONF_PROPERTY = "urlmeta.tags";
    private static String[] urlMetaTags;
    private Configuration conf;

    @Override
    public Collection<WebPage.Field> getFields() {
        return Collections.singletonList(WebPage.Field.METADATA);
    }

    @Override
    public Configuration getConf() {
        return conf;
    }

    @Override
    public void setConf(Configuration arg0) {
        conf = arg0;
    }

    @Override
    public NutchDocument filter(NutchDocument doc, String url, WebPage page)
            throws IndexingException {


        LOG.error(new String(page.getContent().array()));
        doc.getDocumentMeta().add("rawHtml",  new String(page.getContent().array()));
             return doc;
//        page.getContent();
        //try {
//            Metadata metadata = parseResult.get(content.getUrl()).getData().getParseMeta();
            //byte[] rawContent = content.getContent();
            //String str = new String(rawContent, "UTF-8");
            //metadata.add("rawcontent", str);

        //} catch (UnsupportedEncodingException ex) {
        //    Logger.getLogger(NoTrimHTMLParseFilter.class.getName()).log(Level.SEVERE, null, ex);
       // }
       // return null;
    }
}