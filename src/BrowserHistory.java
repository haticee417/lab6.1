public class BrowserHistory {

    private Node.Stack backStack;
    private Node.Stack forwardStack;
    private String currentPage;

    public BrowserHistory() {
        backStack = new Node.Stack();
        forwardStack = new Node.Stack();
        currentPage = null;
    }

    public void visit(String url) {
        if (currentPage != null) {
            backStack.push(currentPage);
        }

        currentPage = url;
        clearStack(forwardStack);

        System.out.println("Visited: " + currentPage);
    }

    public void goBack() {
        if (backStack.isEmpty()) {
            System.out.println("Cannot go back!");
            return;
        }

        forwardStack.push(currentPage);
        currentPage = backStack.pop();

        System.out.println("Went back to: " + currentPage);
    }

    public void goForward() {
        if (forwardStack.isEmpty()) {
            System.out.println("Cannot go forward!");
            return;
        }

        backStack.push(currentPage);
        currentPage = forwardStack.pop();

        System.out.println("Went forward to: " + currentPage);
    }

    public void displayCurrentPage() {
        if (currentPage == null) {
            System.out.println("No page is open.");
        } else {
            System.out.println("Current page: " + currentPage);
        }
    }

    private void clearStack(Node.Stack stack) {
        while (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public void displayHistory() {
        System.out.println("\n========== BROWSER HISTORY ==========");

        System.out.println("Current page: " + currentPage);

        System.out.print("Back stack (top -> bottom): ");
        backStack.display();

        System.out.print("Forward stack (top -> bottom): ");
        forwardStack.display();

        System.out.println("=====================================");
    }

    static class Node {
        String data;
        Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }

        public static class Stack {

            private Node top;

            public Stack() {
                top = null;
            }

            public void push(String value) {
                Node newNode = new Node(value);
                newNode.next = top;
                top = newNode;
            }

            public String pop() {
                if (isEmpty()) {
                    return null;
                }

                String data = top.data;
                top = top.next;
                return data;
            }

            public String peek() {
                if (isEmpty()) {
                    return null;
                }

                return top.data;
            }

            public boolean isEmpty() {
                return top == null;
            }

            public void display() {
                if (isEmpty()) {
                    System.out.println("Empty");
                    return;
                }

                Node current = top;

                while (current != null) {
                    System.out.print(current.data);

                    if (current.next != null) {
                        System.out.print(" -> ");
                    }

                    current = current.next;
                }

                System.out.println();
            }
        }
    }

    public static class BrowserDemo {

        public static void main(String[] args) {

            BrowserHistory browser = new BrowserHistory();

            browser.visit("google.com");
            browser.visit("youtube.com");
            browser.goBack();

            browser.visit("Facebook.com");
            browser.goBack();

            browser.goForward();

            browser.visit("stackoverflow.com");

            browser.displayCurrentPage();

            browser.displayHistory();
        }
    }
}