
/*--------------------------------------------------------------------------
 * Author:          Shawn Wiley
 * Written:         3/28/2012
 * Last Updated:    3/28/2012
 * Class:           CSCI 5100U
 * 
 *      * Complilation:    javac MazeFactory.java
 * 
 *      * Execution:       java MazeFactory <Text File>
 * 
 *  ShawnPathBuilder uses the tree algorithm to find a path.
 * 
 *--------------------------------------------------------------------------*/
import java.util.ArrayList;
import java.util.List;

public class ShawnPathBuilder implements PathBuilder {

    static int whichPath = 1;   // 0 = shortest path, 1 = longest path
    private MazeWithPath maze;
    private Tree<PathRoom> pathTree;
    private List<Node<PathRoom>> paths;
    private List<PathRoom> path;
    // reference to a mazeWithPath

    public void init(MazeWithPath maze) {
        this.maze = maze;
        this.pathTree = new Tree<PathRoom>();
        this.paths = new ArrayList<Node<PathRoom>>();
        this.path = new ArrayList<PathRoom>();
        maze.start().setOnPath(true);
        maze.finish().setOnPath(true);
    }

    //  makes a tree with addToTree() recursively checking all paths to a dead end.
    //  then uses addToList to find a path in the tree
    //  the sets the rooms on path
    public void findPath() {
        pathTree.setRootElement(new Node(maze.start()));
        addToTree(pathTree.getRootElement());

        if (maze.hasPath()) {
            switch (whichPath) {
                case 0:
                    getShortestPath();
                    break;
                case 1:
                    getLongestPath();
                    break;
            }
            for (PathRoom i : path) {
                i.setOnPath(true);
            }
        }
    }

    //  add rooms to the tree recursively by adding a room next door as a child til
    //  it reaches a dead end.
    public void addToTree(Node<PathRoom> node) {
        if (node.getData().isFinish()) {
            maze.setHasPath(true);
            paths.add(node);
            return;
        }
        if (node.getData().getSide(Dir2D.N).enter() && !node.isInPath()) {
            node.addChild(new Node(maze.roomAt(new Position(node.getData().getPosition().rowNum() - 1, node.getData().getPosition().colNum()))));
            addToTree(node.getChildren().get(node.getNumberOfChildren() - 1));
        }
        if (node.getData().getSide(Dir2D.W).enter() && !node.isInPath()) {
            node.addChild(new Node(maze.roomAt(new Position(node.getData().getPosition().rowNum(), node.getData().getPosition().colNum() - 1))));
            addToTree(node.getChildren().get(node.getNumberOfChildren() - 1));
        }
        if (node.getData().getSide(Dir2D.E).enter() && !node.isInPath()) {
            node.addChild(new Node(maze.roomAt(new Position(node.getData().getPosition().rowNum(), node.getData().getPosition().colNum() + 1))));
            addToTree(node.getChildren().get(node.getNumberOfChildren() - 1));
        }
        if (node.getData().getSide(Dir2D.S).enter() && !node.isInPath()) {
            node.addChild(new Node(maze.roomAt(new Position(node.getData().getPosition().rowNum() + 1, node.getData().getPosition().colNum()))));
            addToTree(node.getChildren().get(node.getNumberOfChildren() - 1));
        }
        if (!node.hasPathRoomAsChild(node, maze.finish())) {
            node.clearChildren();
        }
    }

    public void getShortestPath() {
        List<Node<PathRoom>> list = paths.get(0).listOfParents();
        for (Node<PathRoom> i : paths) {
            if (i.listOfParents().size() < list.size()) {
                list = i.listOfParents();
            }
        }
        for (Node<PathRoom> i : list) {
            path.add(i.getData());
        }
    }

    public void getLongestPath() {
        List<Node<PathRoom>> list = new ArrayList<Node<PathRoom>>();
        for (Node<PathRoom> i : paths) {
            if (i.listOfParents().size() > list.size()) {
                list = i.listOfParents();
            }
        }
        for (Node<PathRoom> i : list) {
            path.add(i.getData());
        }
    }

    // displays that path in the console

    public void displayPath() {
        if (maze.hasPath()) {
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i).display());
            }
            System.out.println(maze.finish().display());
        } else {
            System.out.println("There is no path.");
        }
    }

    public class Tree<PathRoom> {

        private Node<PathRoom> rootElement;

        public Tree() {
            super();
        }

        public Node<PathRoom> getRootElement() {
            return this.rootElement;
        }

        public void setRootElement(Node<PathRoom> rootElement) {
            this.rootElement = rootElement;
        }
    }

    public class Node<PathRoom> {

        public PathRoom data;
        public Node<PathRoom> parent;
        public List<Node<PathRoom>> children;

        public Node() {
            super();
            parent = null;
        }

        public Node(PathRoom data) {
            this();
            setData(data);
        }

        public List<Node<PathRoom>> getChildren() {
            if (this.children == null) {
                return new ArrayList<Node<PathRoom>>();
            }
            return this.children;
        }

        public int getNumberOfChildren() {
            if (children == null) {
                return 0;
            }
            return children.size();
        }

        public Node<PathRoom> getParent() {
            return parent;
        }

        public void addChild(Node<PathRoom> child) {
            if (children == null) {
                children = new ArrayList<Node<PathRoom>>();
            }
            child.parent = this;
            children.add(child);
        }

        public void clearChildren() {
            if (children != null) {
                for (Node<PathRoom> i : children) {
                    i = null;
                }
            }
            children = null;
        }

        public PathRoom getData() {
            return this.data;
        }

        public void setData(PathRoom data) {
            this.data = data;
        }

        public boolean hasPathRoomAsChild(Node<PathRoom> node, PathRoom p) {
            List<Node<PathRoom>> list = new ArrayList<Node<PathRoom>>();
            walk(node, list);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).getData().hashCode() == p.hashCode()) {
                    list = null;
                    return true;
                }
            }
            list = null;
            return false;

        }

        private void walk(Node<PathRoom> element, List<Node<PathRoom>> list) {
            list.add(element);
            for (Node<PathRoom> data : element.getChildren()) {
                walk(data, list);
            }
        }

        public List<Node<PathRoom>> listOfParents() {
            List<Node<PathRoom>> list = new ArrayList<Node<PathRoom>>();
            Node<PathRoom> element = parent;
            while (element != null) {
                list.add(element);
                element = element.getParent();
            }

            return list;
        }

        public boolean isInPath() {
            List<Node<PathRoom>> list = listOfParents();

            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getData().hashCode() == list.get(j).getData().hashCode()) {
                        list = null;
                        return true;
                    }
                }
            }
            list = null;
            return false;
        }
    }
}