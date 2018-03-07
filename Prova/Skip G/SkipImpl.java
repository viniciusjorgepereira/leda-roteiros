1   package adt.skipList;
2   
3   public class SkipListImpl<T> implements SkipList<T> {
4   
5   	protected SkipListNode<T> root;
6   	protected SkipListNode<T> NIL;
7   
8   	protected int maxHeight;
9   
10  	protected double PROBABILITY = 0.5;
11  
12  	public SkipListImpl(int maxHeight) {
13  		this.maxHeight = maxHeight;
14  		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
15  		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
16  		connectRootToNil();
17  	}
18  
19  	/**
20  	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
21  	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
22  	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
23  	 * metodo deve conectar apenas o forward[0].
24  	 */
25  	private void connectRootToNil() {
26  		for (int i = 0; i < maxHeight; i++) {
27  			root.forward[i] = NIL;
28  		}
29  	}
30  
31  	
32  	@Override
33  	public void insert(int key, T newValue, int height) {
34  		
35  		SkipListNode<T> [] update = new SkipListNode [this.maxHeight];
36  		SkipListNode<T> node = this.root;
37  		int index;
38  		//Pesquisa o local
39  		for(int i = this.maxHeight; i > 0; i--){ 
40  			index = i - 1;
41  			while(node.forward[index] != null && node.forward[index].getKey() < key){
42  				node = node.forward[index];
43  			}
44  			update[index] = node; // Guarda o caminho
45  		}
46  		node = node.forward[0];
47  		if(node.getKey() == key){
48  			node.setValue(newValue);
49  		}
50  		else{
51  			int v = height;
52  			node = new SkipListNode<T>(key, v, newValue);
53  			for(int i = 0; i < v; i++){
54  				node.forward[i] = update[i].forward[i];
55  				update[i].forward[i] = node;
56  			}
57  		}
58  
59  	}
60  
61  	@Override
62  	public void remove(int key) {
63  		
64  		SkipListNode<T> [] update = new SkipListNode [this.maxHeight]; 
65  		SkipListNode<T> node = this.root;
66  		int index;
67  		//Pesquisa o local
68  		for(int i = this.maxHeight; i > 0; i--){
69  			index = i - 1;
70  			while(node.forward[index] != null && node.forward[index].getKey() < key){
71  				node = node.forward[index];
72  			}
73  			update[index] = node; // Guarda o caminho
74  		}
75  		node = node.forward[0];
76  		if(node.getKey() == key){
77  			//Ajeita os ponteiros
78  			for(int i = 0; i < this.maxHeight; i++){
79  				if(update[i].forward[i] != node)
80  					break;
81  				else
82  					update[i].forward[i] = node.forward[i];
83  			}
84  		}
85  	}
86  
87  	@Override
88  	public int height() {
89  		
90  		int largest = 0;
91  		if(this.size() != 0) {
92  			SkipListNode<T> aux = this.root.forward[0];
93  			while(!aux.equals(NIL)) {
94  				if(aux.height() > largest) {
95  					largest = aux.height();
96  				}
97  				aux = aux.forward[0];
98  			}
99  		}
100 		return largest;
101 	}
102 
103 	@Override
104 	public SkipListNode<T> search(int key) {
105 		
106 		SkipListNode<T> retorno;
107 		SkipListNode<T> node = this.root;
108 		int index;
109 		
110 		for(int i = this.maxHeight; i > 0; i--){
111 			index = i - 1;
112 			while(node.forward[index] != null && node.forward[index].getKey() < key){
113 				node = node.forward[index];
114 			}
115 		}
116 		node = node.forward[0]; 
117 		if(node.getKey() == key){
118 			retorno = node;
119 		}
120 		else
121 			retorno = null;
122 		
123 		return retorno;
124 	}
125 
126 	@Override
127 	public int size() {
128 		
129 		int size = 0;
130 		SkipListNode<T> aux = this.root;
131 		while(aux.forward[0] != NIL) {
132 			aux = aux.forward[0];
133 			size++;
134 		}
135 		return size;
136 	}
137 
138 	@Override
139 	public SkipListNode<T>[] toArray() {
140 		
141 		SkipListNode<T> [] array = new SkipListNode [this.size() + 2];
142 		SkipListNode<T> aux = this.root;
143 		int index = 0;
144 		while(index != this.size() + 2) {
145 			array[index] = aux;
146 			aux = aux.forward[0];
147 			index++;
148 		}
149 		
150 		return array;
151 	}
152 
153 }