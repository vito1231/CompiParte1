


package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SinglePackageDeclaration extends PackageDeclaration {

  public SinglePackageDeclaration (Declaration dAST,SourcePosition thePosition) {
    super (thePosition);
    D = dAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitSinglePackageDeclaration(this, o);
  }
  public Declaration D;
}