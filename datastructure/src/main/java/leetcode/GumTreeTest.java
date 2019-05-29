package leetcode;

import com.github.gumtreediff.client.Run;
import com.github.gumtreediff.gen.Generators;
import com.github.gumtreediff.gen.jdt.JdtTreeGenerator;
import com.github.gumtreediff.io.TreeIoUtils;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;

import java.io.IOException;

public class GumTreeTest {
    public static void main(String[] args) throws IOException {
        Run.initGenerators();
        String file = "F:\\gitwork\\myPractice\\datastructure\\src\\main\\java\\test\\TreeTest.java";
        TreeContext tc = new JdtTreeGenerator().generateFromString(file);; // retrieve the default generator for the file
        ITree t = tc.getRoot(); // return the root of the tree
        System.out.println(TreeIoUtils.toLisp(tc).toString()); // displays the tree in LISP syntax
    }
}
