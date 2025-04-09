//class
public class UndoRedoManager<T> {
    
    //Nodes
    private class Node {

        private T state;
        private Node prev;
        private Node next;

        private Node(T state) {
            this.state = state;
        }

    }

    private Node currentState;

    public T undo() {
        if (currentState == null || currentState.prev == null) {
            return null;
        }
    
        currentState = currentState.prev;
        return currentState.state;
    }

    public void addState(T newState) {
        Node newNode = new Node(newState);
    
        if (currentState == null) {  
            currentState = newNode;  
            return;  
        }
    
        currentState.next = newNode;  
        newNode.prev = currentState;  
        currentState = newNode;  
    }

    public T redo() {
        if (currentState == null || currentState.next == null) {
            return null;
        }
        
        currentState = currentState.next;
        return currentState.state;
    }

    public static void main(String[] args) {
        UndoRedoManager<String> manager = new UndoRedoManager<>();

        manager.addState("State 1");
        manager.addState("State 2");
        manager.addState("State 3");

        System.out.println(manager.undo()); 
        System.out.println(manager.undo()); 
        System.out.println(manager.redo()); 
        
        manager.addState("State 4"); 

        System.out.println(manager.undo()); 
        System.out.println(manager.redo()); 
    }
}
