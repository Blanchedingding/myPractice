package tree;

public class TrieTree {

    private class TrieNode{
        String word ;//存储的单词是什么
        TrieNode[] kids = new TrieNode[26];//26个字母可以加在后面
        boolean isWord = false ;//是否是一个单词
        char singleChar;//存储的单个字母
        int num;//有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数

    }

    TrieNode root;

    public TrieTree(){
        this.root = new TrieNode();
    }

    public void insert(String s){
        if( null == s ||  s.length() == 0 ) return;

        TrieNode curNode = root;
        char[] charList = s.toCharArray();
        for(int i = 0; i < charList.length; i++){
            int pos = charList[i] - 'a';
            if( null == curNode.kids[pos] ){
                curNode.kids[pos] = new TrieNode();
                curNode.kids[pos].singleChar = charList[i];
            } else {
                curNode.kids[pos].num ++;
            }
            curNode = curNode.kids[pos];
        }
        curNode.word = s;
    }

    public boolean search(String s){
        if( null == s ||  s.length() == 0 ) return false;
        TrieNode curNode = root;
        char[] charList = s.toCharArray();
        for(int i = 0; i < charList.length; i++){
            int pos = charList[i] - 'a';
            if( null == curNode.kids[pos] ) return false;
            curNode = curNode.kids[pos];
        }
        if( null != curNode.word) return true;
        else return false;
    }

    //前序遍历字典树.
    public void preTraverse(TrieNode node) {
        if( node != null) {
            System.out.print( node.singleChar + "-" );
            for( TrieNode child: node.kids ){
                preTraverse(child);
            }
        }
    }


    public static void main(String[] args) {
        TrieTree tree=new TrieTree();
        String[] strs= {"banana","band","bee","absolute","acm",};
        for(String str:strs) {
            tree.insert(str);
        }
        System.out.println(tree.search("abc"));
        tree.preTraverse(tree.root);
        System.out.println();
    }
}
