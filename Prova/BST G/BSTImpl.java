1   package adt.bst;
2   
3   import java.util.ArrayList;
4   
5   public class BSTImpl<T extends Comparable<T>> implements BST<T> {
6   
7      protected BSTNode<T> root;
8   
9      public BSTImpl() {
10        root = new BSTNode<T>(); 
11     }
12     
13  
14     public BSTNode<T> getRoot() {
15        return this.root;
16     }
17     
18  
19     @Override
20     public boolean isEmpty() {
21        return root.isEmpty();
22     }
23     
24  
25     @Override
26     public int height() {
27  
28        return heightRec(root, -1);
29     }
30     
31  
32     private int heightRec(BSTNode<T> node, int alturaAtual) {
33  
34        if (!node.isEmpty()) {
35           int left = heightRec((BSTNode<T>) node.getLeft(), alturaAtual + 1);
36           int right = heightRec((BSTNode<T>) node.getRight(), alturaAtual + 1);
37  
38           alturaAtual = Math.max(left, right);
39        }
40        return alturaAtual;
41     }
42     
43  
44     @Override
45     public BSTNode<T> search(T element) {
46  
47        if (this.isEmpty())
48           return new BSTNode.Builder().build();
49  
50        return this.search(element, this.root);
51     }
52     
53  
54     private BSTNode<T> search(T element, BSTNode<T> node) {
55  
56        BSTNode<T> saida;
57  
58        if (node.isEmpty())
59           saida = new BSTNode.Builder().build();
60  
61        else if (element.compareTo(node.getData()) == 0)
62           saida = node;
63        else if (element.compareTo(node.getData()) < 0)
64           saida = search(element, (BSTNode<T>) node.getLeft());
65        else
66           saida = search(element, (BSTNode<T>) node.getRight());
67  
68        return saida;
69  
70     }
71     
72  
73     @Override
74     public void insert(T element) {
75  
76        if (this.isEmpty())
77           root = newNode(element, null);// e o parent?
78  
79        else
80           insertR(element, root);
81  
82     }
83     
84  
85     private void insertR(T element, BSTNode<T> node) {
86  
87        if (node.isEmpty()) {
88           node.setData(element);
89           node.setLeft(new BSTNode.Builder().build());
90           node.setRight(new BSTNode.Builder().build());
91  
92           node.getLeft().setParent(node);
93           node.getRight().setParent(node);
94        } else {
95           if (element.compareTo(node.getData()) < 0)
96              insertR(element, (BSTNode<T>) node.getLeft());
97  
98           else if (element.compareTo(node.getData()) > 0)
99              insertR(element, (BSTNode<T>) node.getRight());
100       }
101    }
102    
103 
104    private BSTNode<T> newNode(T element, BSTNode<T> pai) {
105 
106       BSTNode<T> newNode = new BSTNode.Builder().data(element).parent(pai).left(new BSTNode.Builder().build())
107             .right(new BSTNode.Builder().build()).build();
108 
109       newNode.getLeft().setParent(newNode);
110       newNode.getRight().setParent(newNode);
111 
112       return newNode;
113    }
114    
115 
116    @Override
117    public BSTNode<T> maximum() {
118 
119       if (this.isEmpty())
120          return null;
121       else
122          return maximumRec(root);
123    }
124    
125 
126    private BSTNode<T> maximumRec(BSTNode<T> node) {
127 
128       if (!node.getRight().isEmpty()) {
129          return maximumRec((BSTNode<T>) node.getRight());
130       } else
131          return node;
132    }
133    
134 
135    @Override
136    public BSTNode<T> minimum() {
137 
138       BSTNode<T> min;
139 
140       if (this.isEmpty())
141          return null;
142       else
143          return minimumRec(root);
144    }
145    
146 
147    private BSTNode<T> minimumRec(BSTNode<T> node) {
148 
149       if (!node.getLeft().isEmpty()) {
150          return minimumRec((BSTNode<T>) node.getLeft());
151       } else
152          return node;
153    }
154 
155    
156    @Override
157    public BSTNode<T> sucessor(T element) {
158 
159       BSTNode<T> node = this.search(element);
160       if (!node.isEmpty()) {
161          if (!node.getRight().isEmpty())
162             return minimumRec((BSTNode<T>) node.getRight());
163          else {
164             BSTNode<T> parent = (BSTNode<T>) node.getParent();
165             
166             while (parent != null && parent.getData().compareTo(node.getData()) < 0) {
167                node = parent;
168                parent = (BSTNode<T>) node.getParent();
169             }
170 
171             return parent;
172          }
173       }
174       return null;
175    }
176 
177    
178    @Override
179    public BSTNode<T> predecessor(T element) {
180 
181       BSTNode<T> node = this.search(element);
182       if (!node.isEmpty()) {
183          if (!node.getLeft().isEmpty())
184             return maximumRec((BSTNode<T>) node.getLeft());
185          else {
186             BSTNode<T> parent = (BSTNode<T>) node.getParent();
187 
188             while (parent != null && parent.getData().compareTo(node.getData()) > 0) {
189                node = parent;
190                parent = (BSTNode<T>) node.getParent();
191             }
192 
193             return parent;
194          }
195       }
196       return null;
197 
198    }
199 
200    
201    @Override
202    public void remove(T element) {
203 
204 	   BSTNode<T> node = search(element);
205 
206 	   if (!node.isEmpty()) {
207 		   if(node.isLeaf()) {
208 				node.setData(null);
209 			}
210 			else if (hasOnlyOneChild(node)) {
211 				if (node.getParent() != null) {
212 					if (!node.getParent().getLeft().equals(node)) {
213 						if (!node.getLeft().isEmpty()) {
214 							node.getParent().setRight(node.getLeft());
215 							node.getLeft().setParent(node.getParent());
216 						} else {
217 							node.getParent().setRight(node.getRight());
218 							node.getRight().setParent(node.getParent());
219 						}
220 					} else {
221 						if (!node.getLeft().isEmpty()) {
222 							node.getParent().setLeft(node.getLeft());
223 							node.getLeft().setParent(node.getParent());
224 						} else {
225 							node.getParent().setLeft(node.getRight());
226 							node.getRight().setParent(node.getParent());
227 						}
228 					}
229 				} else {
230 					if (node.getLeft().isEmpty()) {
231 						root = (BSTNode<T>) node.getRight();
232 					} else {
233 						root = (BSTNode<T>) node.getLeft();
234 					}
235 					root.setParent(null);
236 				}
237 
238 			} else {
239 				T sucessor = sucessor(node.getData()).getData();
240 				remove(sucessor);
241 				node.setData(sucessor);
242 			}
243 		}
244    }
245    
246    
247    private boolean hasOnlyOneChild(BSTNode<T> node){
248 	   
249 	   return (node.getLeft().isEmpty() && !node.getRight().isEmpty()) || (!node.getLeft().isEmpty() && node.getRight().isEmpty());
250    }
251    
252 
253 @Override
254    public T[] preOrder() {
255 
256       T[] arr = (T[]) new Comparable[this.size()];
257       ArrayList<T> aux = new ArrayList<>();
258 
259       if (!this.isEmpty()) {
260          preOrderRec(root, aux);
261 
262          for (int index = 0; index < aux.size(); index++)
263             arr[index] = aux.get(index);
264       }
265 
266       return arr;
267    }
268 
269 
270    private void preOrderRec(BSTNode<T> node, ArrayList<T> array) {
271 
272       if (!node.isEmpty()) {
273          array.add(node.getData());
274          preOrderRec((BSTNode<T>) node.getLeft(), array);
275          preOrderRec((BSTNode<T>) node.getRight(), array);
276       }
277    }
278 
279    
280    @Override
281    public T[] order() {
282 
283       T[] arr = (T[]) new Comparable[this.size()];
284       ArrayList<T> aux = new ArrayList<>();
285 
286       if (!this.isEmpty()) {
287          OrderRec(root, aux);
288 
289          for (int index = 0; index < aux.size(); index++)
290             arr[index] = aux.get(index);
291       }
292       return arr;
293    }
294    
295 
296    private void OrderRec(BSTNode<T> node, ArrayList<T> array) {
297 
298       if (!node.isEmpty()) {
299          OrderRec((BSTNode<T>) node.getLeft(), array);
300          array.add(node.getData());
301          OrderRec((BSTNode<T>) node.getRight(), array);
302       }
303    }
304    
305    
306    @Override
307    public T[] postOrder() {
308 	   
309 	   T[] arr = (T[]) new Comparable[this.size()];
310 	   ArrayList<T> aux = new ArrayList<>();
311 
312 	   if (!this.isEmpty()) {
313 		   postOrderRec(root, aux);
314 
315 	       for (int index = 0; index < aux.size(); index++)
316 	    	   arr[index] = aux.get(index);
317 	      }
318 
319 	   return arr;
320    }
321 
322 
323    private void postOrderRec(BSTNode<T> node, ArrayList<T> array) {
324 	
325 	   if (!node.isEmpty()) {
326 	         postOrderRec((BSTNode<T>) node.getLeft(), array);
327 	         postOrderRec((BSTNode<T>) node.getRight(), array);
328 	         array.add(node.getData());
329 	      }
330 }
331    
332 
333 /**
334     * This method is already implemented using recursion. You must understand
335     * how it work and use similar idea with the other methods.
336     */
337    @Override
338    public int size() {
339       return size(root);
340    }
341    
342 
343    private int size(BSTNode<T> node) {
344       int result = 0;
345       // base case means doing nothing (return 0)
346       if (!node.isEmpty()) { // indusctive case
347          result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
348       }
349       return result;
350    }
351 
352 
353 }