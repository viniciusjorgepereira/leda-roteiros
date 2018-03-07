package adt.skipList.fromBST;

import adt.bst.BST;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.skipList.SkipListImpl;
import adt.skipList.SkipListNode;

public class SkipListFromBSTImpl extends SkipListImpl<Integer> implements SkipListFromBST<Integer> {

   public SkipListFromBSTImpl(int maxHeight) {
      super(maxHeight);
   }

   public void importFromBST(BST<Integer> bst) {
      Integer[] array = bst.preOrder();

      int altura = bst.height();
      this.root = resetSkip(this.root, altura);

      for (int i = 0; i < array.length; i++) {
         BTNode<Integer> no = bst.search(array[i]);
         BTNode<Integer> parent = no.getParent();

         int alt = alturaNoParent(no, parent);

         insert(parent.getData(), parent.getData(), alt);
      }

   }

   private SkipListNode<Integer> resetSkip(SkipListNode<Integer> root, int altura) {
      return new SkipListNode<Integer>(Integer.MIN_VALUE, altura, null);
   }

   private int alturaNoParent(BTNode<Integer> no, BTNode<Integer> parent) {
      int alt = 1;
      BSTNode<Integer> node = (BSTNode<Integer>) no;
      if (node.getParent() != parent) {
         node = (BSTNode<Integer>) node.getParent();
         alt += 1;
      }
      return alt;
   }

}
