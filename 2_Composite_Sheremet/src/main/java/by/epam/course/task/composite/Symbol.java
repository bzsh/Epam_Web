package by.epam.course.task.composite;

import java.util.List;

public class Symbol implements Component {
    private char symbol;
    private SymbolType symbolType;

    public Symbol(char symbol, SymbolType symbolType) {
        this.symbol = symbol;
        this.symbolType = symbolType;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        if (this.symbol != symbol.symbol) return false;
        return symbolType == symbol.symbolType;
    }

    @Override
    public int hashCode() {
        int result = (int) symbol;
        result = 31 * result + (symbolType != null ? symbolType.hashCode() : 0);
        return result;
    }

    @Override
    public void add(Component component) {
    }

    @Override
    public CompositeType getCompositeType() {
        return null;
    }

    @Override
    public List<Component> getComponents() {
        return null;
    }
}
