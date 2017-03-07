package me.jcala.tip.data.structure.tree_by_list;

public class BinaryTree {
    private TreeNode root;
    public BinaryTree(int[] data) {
       for (int i:data){
           addValue(i);
       }
    }
    private void addValue(int value){
      TreeNode currentNode=root;
      if (root==null){
          root=new TreeNode(value);
          return;
      }
      while (true){//循环判断要插入的位置
          if (value<currentNode.getValue()){//小于当前value的值，则为左子树
              if(currentNode.getLeftNode()==null){
                  currentNode.setLeftNode(new TreeNode(value));
                  return;
              }else currentNode=currentNode.getLeftNode();
          }else {////大于当前value的值，则为右子树
              if (currentNode.getRightNode() == null) {
                  currentNode.setRightNode(new TreeNode(value));
                  return;
              } else currentNode=currentNode.getRightNode();
          }
      }
    }
}