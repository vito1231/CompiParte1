
package Triangle.TreeXml;

import Triangle.AbstractSyntaxTrees.Program;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class XmlGenerator {
    
    //could be a dialog
    public static final String xmlFilePath = "C:\\Users\\nikos7\\Desktop\\files\\xmlfile.xml";
    
    private XmlVisitor visitor;
    private Program theAst;
    private Document document;
    DocumentBuilderFactory documentFactory;
    DocumentBuilder documentBuilder;

    public XmlGenerator(Program ast) throws ParserConfigurationException{
        theAst = ast;

        documentFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();

        visitor = new XmlVisitor(document); 
    }



    public Element getXmlAstRepresentation(){

        
        Element root = (Element)theAst.visit(visitor,null);
        return root;
       

  
    }

    public void SaveXmlRepresentation(String savePath){
        Element root = getXmlAstRepresentation();
        Document savedDocument = documentBuilder.newDocument();
        savedDocument.appendChild(root);
        //saving code
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(savePath));
            transformer.transform(domSource, streamResult);
            System.out.println("Done saving ast to " + savePath);
        }catch(Exception e){
            System.out.println("Error saving ast to " + savePath);
        }
        
    }

}