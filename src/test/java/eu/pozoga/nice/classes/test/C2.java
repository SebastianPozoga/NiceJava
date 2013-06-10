package eu.pozoga.nice.classes.test;

import eu.pozoga.nice.classes.ex.MethodAnn;
import eu.pozoga.nice.classes.ex.TestInterface;
import eu.pozoga.nice.classes.ex.TypeAnn;

@TypeAnn
public class C2 implements TestInterface{
    
    public String string3;    
    
    public String myText(){
        return "myTextResult";
    }
    
    @MethodAnn
    public String methodWithAnnotation(){
        return "method1WithAnnotation_result";
    }
    
    public String methodWithoutAnnotation(){
        return "methodWithoutAnnotation_result";
    }
}
