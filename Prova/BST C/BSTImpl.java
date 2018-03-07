1   package adt.bst;
2   
3   import java.util.ArrayList;
4   
5   public class BSTImpl<T extends Comparable<T>> implements BST<T> {
6   
7   	protected BSTNode<T> root;
8   
9   	public BSTImpl() {
10  		root = new BSTNode<T>();
11  	}
12  
13  	public BSTNode<T> getRoot() {
14  		return this.root;
15  	}
16  
17  	@Override
18  	public boolean isEmpty() {
19  		return root.isEmpty();
20  	}
21  
22  	@Override
23  	public int height() {
24  		return this.height(this.root);
25  	}
26  
27  	private int height(BSTNode<T> node) {
28  		int result = -1;
29  		if (!node.isEmpty()) {
30  			int left = height((BSTNode<T>) node.getLeft());
31  			int right = height((BSTNode<T>) node.getRight());
32  
33  			result = Math.max(left, right) + 1;
34  		}
35  		return result;
36  	}
37  
38  	@Override
39  	public BSTNode<T> search(T element) {
40  		return this.search(this.root, element);
41  	}
42  
43  	private BSTNode<T> search(BSTNode<T> node, T element) {
44  		BSTNode<T> result = null;
45  		if (element != null) {
46  			if (node.isEmpty() || node.getData().compareTo(element) == 0) {
47  				result = node;
48  			} else if (node.getData().compareTo(element) > 0) {
49  				result = search((BSTNode<T>) node.getLeft(), element);
50  			} else {
51  				result = search((BSTNode<T>) node.getRight(), element);
52  			}
53  		}
54  		return result;
55  	}
56  
57  	@Override
58  	public void insert(T element) {
59  		if (element != null) {
60  			this.insert(element, this.root, new BSTNode<>());
61  		}
62  	}
63  
64  	private void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
65  		if (node.isEmpty()) {
66  			node.setData(element);
67  			node.setLeft(new BSTNode<>());
68  			node.setRight(new BSTNode<>());
69  			node.setParent(parent);
70  		} else if (element.compareTo(node.getData()) > 0) {
71  			insert(element, (BSTNode<T>) node.getRight(), node);
72  		} else {
73  			insert(element, (BSTNode<T>) node.getLeft(), node);
74  		}
75  	}
76  
77  	@Override
78  	public BSTNode<T> maximum() {
79  		return treeMaximum(this.root);
80  	}
81  
82  	private BSTNode<T> treeMaximum(BSTNode<T> aux) {
83  		BSTNode<T> result = null;
84  		BSTNode<T> node = aux;
85  		while (!node.isEmpty()) {
86  			result = node;
87  			node = (BSTNode<T>) node.getRight();
88  		}
89  		return result;
90  	}
91  
92  	@Override
93  	public BSTNode<T> minimum() {
94  		return treeMinimum(this.root);
95  	}
96  
97  	private BSTNode<T> treeMinimum(BSTNode<T> aux) {
98  		BSTNode<T> result = null;
99  		BSTNode<T> node = aux;
100 		while (!node.isEmpty()) {
101 			result = node;
102 			node = (BSTNode<T>) node.getLeft();
103 		}
104 		return result;
105 	}
106 
107 	@Override
108 	public BSTNode<T> sucessor(T element) {
109 		BSTNode<T> result = null;
110 		BSTNode<T> node = search(element);
111 		if (!node.isEmpty() && !node.equals(this.maximum())) {
112 			if (!node.getRight().isEmpty()) {
113 				result = this.treeMinimum((BSTNode<T>) node.getRight());
114 			} else {
115 				result = (BSTNode<T>) node.getParent();
116 				while (!result.isEmpty() && node.equals(result.getRight())) {
117 					node = result;
118 					result = (BSTNode<T>) result.getParent();
119 				}
120 			}
121 		}
122 		return result;
123 	}
124 
125 	@Override
126 	public BSTNode<T> predecessor(T element) {
127 		BSTNode<T> result = null;
128 		BSTNode<T> node = search(element);
129 		if (!node.isEmpty() && !node.equals(this.minimum())) {
130 			if (!node.getLeft().isEmpty()) {
131 				result = this.treeMaximum((BSTNode<T>) node.getLeft());
132 			} else {
133 				result = (BSTNode<T>) node.getParent();
134 				while (!result.isEmpty() && node.equals(result.getLeft())) {
135 					node = result;
136 					result = (BSTNode<T>) result.getParent();
137 				}
138 			}
139 		}
140 		return result;
141 	}
142 
143 	@Override
144 	public void remove(T element) {
145 		BSTNode<T> node = search(element);
146 		if (node != null && !node.isEmpty()) {
147 			if (node.isLeaf()) {
148 				node.setData(null);
149 				node.setLeft(null);
150 				node.setRight(null);
151 				node.setParent(null);
152 			} else if (numberOfChildren(node) == 1) {
153 				if (!node.equals(this.root)) {
154 					if (node.getParent().getLeft().equals(node)) {
155 						if (!node.getLeft().isEmpty()) {
156 							node.getParent().setLeft(node.getLeft());
157 							node.getLeft().setParent(node.getParent());
158 						} else {
159 							node.getParent().setLeft(node.getRight());
160 							node.getRight().setParent(node.getParent());
161 						}
162 					} else {
163 						if (!node.getLeft().isEmpty()) {
164 							node.getParent().setRight(node.getLeft());
165 							node.getLeft().setParent(node.getParent());
166 						} else {
167 							node.getParent().setRight(node.getRight());
168 							node.getRight().setParent(node.getParent());
169 						}
170 					}
171 				} else {
172 					T newData = null;
173 					if (!node.getLeft().isEmpty()) {
174 						newData = treeMaximum((BSTNode<T>) node.getLeft()).getData();
175 					} else {
176 						newData = treeMinimum((BSTNode<T>) node.getRight()).getData();
177 					}
178 					remove(newData);
179 					this.root.setData(newData);
180 				}
181 			} 
182 			else {
183 				BSTNode<T> sucessor = sucessor(node.getData());
184 				T newData = sucessor.getData();
185 				remove(sucessor.getData());
186 				node.setData(newData);
187 			}
188 		}
189 	}
190 
191 	private int numberOfChildren(BSTNode<T> node) {
192 		int result = 0;
193 		if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
194 			result = 2;
195 		} else if ((node.getLeft().isEmpty() && !node.getRight().isEmpty())
196 				|| (!node.getLeft().isEmpty() && node.getRight().isEmpty())) {
197 			result = 1;
198 		}
199 		return result;
200 	}
201 
202 	@Override
203 	public T[] preOrder() {
204 		ArrayList<Comparable> array = new ArrayList<Comparable>();
205 		preOrder(array, this.root);
206 		return (T[]) array.toArray(new Comparable[size()]);
207 	}
208 
209 	private void preOrder(ArrayList<Comparable> array, BSTNode<T> node) {
210 		if (!node.isEmpty()) {
211 			array.add(node.getData());
212 			preOrder(array, (BSTNode<T>) node.getLeft());
213 			preOrder(array, ((BSTNode<T>) node.getRight()));
214 		}
215 	}
216 
217 	@Override
218 	public T[] order() {
219 		ArrayList<Comparable> array = new ArrayList<Comparable>();
220 		order(array, this.root);
221 		return (T[]) array.toArray(new Comparable[size()]);
222 
223 	}
224 
225 	private void order(ArrayList<Comparable> array, BSTNode<T> node) {
226 		if (!node.isEmpty()) {
227 			order(array, (BSTNode<T>) node.getLeft());
228 			array.add(node.getData());
229 			order(array, (BSTNode<T>) node.getRight());
230 		}
231 	}
232 
233 	@Override
234 	public T[] postOrder() {
235 		ArrayList<Comparable> array = new ArrayList<Comparable>();
236 		postOrder(array, this.root);
237 		return (T[]) array.toArray(new Comparable[size()]);
238 	}
239 
240 	private void postOrder(ArrayList<Comparable> array, BSTNode<T> node) {
241 		if (!node.isEmpty()) {
242 			postOrder(array, (BSTNode<T>) node.getLeft());
243 			postOrder(array, (BSTNode<T>) node.getRight());
244 			array.add(node.getData());
245 		}
246 	}
247 
248 	/**
249 	 * This method is already implemented using recursion. You must understand how
250 	 * it work and use similar idea with the other methods.
251 	 */
252 	@Override
253 	public int size() {
254 		return size(root);
255 	}
256 
257 	private int size(BSTNode<T> node) {
258 		int result = 0;
259 		// base case means doing nothing (return 0)
260 		if (!node.isEmpty()) { // indusctive case
261 			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
262 		}
263 		return result;
264 	}
265 }