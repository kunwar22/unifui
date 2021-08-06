package in.co.srdt.unif.model.payroll;

import java.util.ArrayList;
import java.util.List;

public class ElementMappingWrapper {
    List<ElementMapping> elementMapping;

    public ElementMappingWrapper() {
        elementMapping = new ArrayList<>();
        elementMapping.add(new ElementMapping());
    }

    public List<ElementMapping> getElementMapping() {
        return elementMapping;
    }

    public void setElementMapping(List<ElementMapping> elementMapping) {
        this.elementMapping = elementMapping;
    }

    @Override
    public String toString() {
        return "ElementMappingWrapper{" +
                "elementMapping=" + elementMapping +
                '}';
    }
}
