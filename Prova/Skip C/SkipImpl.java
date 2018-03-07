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
21  	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
22  	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
23  	 * conectar apenas o forward[0].
24  	 */
25  	private void connectRootToNil() {
26  		for (int i = 0; i < maxHeight; i++) {
27  			root.forward[i] = NIL;
28  		}
29  	}
30  
31  	@Override
32  	public void insert(int key, T newValue, int height) {
33  		if (newValue != null) {
34  
35  			SkipListNode<T>[] atualizacao = new SkipListNode[height];
36  			SkipListNode<T> auxiliar = root;
37  
38  			for (int i = height - 1; i >= 0; i--) {
39  				while (auxiliar.getForward(i) != null && auxiliar.getForward(i).getKey() < key) {
40  					auxiliar = auxiliar.getForward(i);
41  				}
42  				atualizacao[i] = auxiliar;
43  			}
44  
45  			auxiliar = auxiliar.getForward(0);
46  
47  			if (auxiliar.getKey() == key)
48  				auxiliar.setValue(newValue);
49  
50  			else {
51  				ajustar(height, atualizacao);
52  				auxiliar = new SkipListNode<T>(key, height, newValue);
53  				trocaReferencias(height, atualizacao, auxiliar);
54  			}
55  
56  		}
57  	}
58  
59  	private void trocaReferencias(int height, SkipListNode<T>[] atualizacao, SkipListNode<T> auxiliar) {
60  
61  		for (int i = 0; i < height; i++) {
62  
63  			if (atualizacao[i].getForward(i) == null)
64  				auxiliar.getForward()[i] = NIL;
65  
66  			else {
67  				auxiliar.getForward()[i] = atualizacao[i].getForward()[i];
68  				atualizacao[i].getForward()[i] = auxiliar;
69  			}
70  		}
71  	}
72  
73  	private void ajustar(int height, SkipListNode<T>[] atualizacao) {
74  		if (height > maxHeight) {
75  			for (int i = maxHeight; i < height; i++) {
76  				root.getForward()[i] = NIL;
77  			}
78  
79  			maxHeight = height;
80  		}
81  	}
82  
83  	@Override
84  	public void remove(int key) {
85  
86  		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
87  		SkipListNode<T> aux = root;
88  
89  		for (int i = maxHeight - 1; i >= 0; i--) {
90  			if (aux.getForward()[i] != NIL) {
91  				while (aux.getForward()[i].getValue() != null && aux.getForward()[i].getKey() < key)
92  					aux = aux.getForward()[i];
93  			}
94  
95  			update[i] = aux;
96  		}
97  		aux = aux.getForward()[0];
98  
99  		if (aux.getKey() == key) {
100 			for (int i = 0; i < maxHeight; i++) {
101 				if (update[i].getForward()[i] != aux)
102 					break;
103 				update[i].getForward()[i] = aux.getForward()[i];
104 			}
105 		}
106 	}
107 
108 	@Override
109 	public int height() {
110 		int height = maxHeight - 1;
111 
112 		while (height >= 0 && root.getForward(height) == NIL) {
113 			if (height == 0) {
114 				height--;
115 				break;
116 			} else
117 				height--;
118 
119 		}
120 		return height + 1;
121 	}
122 
123 	@Override
124 	public SkipListNode<T> search(int key) {
125 
126 		SkipListNode<T> r = root;
127 		SkipListNode<T> retorno;
128 
129 		for (int i = height() - 1; i >= 0; i--) {
130 
131 			while (r.getForward(i) != null && r.getForward(i).getKey() < key) {
132 				r = r.getForward(i);
133 			}
134 		}
135 		r = r.getForward(0);
136 
137 		if (r.getKey() == key)
138 			retorno = r;
139 		else
140 			retorno = null;
141 
142 		return retorno;
143 
144 	}
145 
146 	@Override
147 	public int size() {
148 		int resultado = 0;
149 
150 		SkipListNode<T> aux = root.getForward(0);
151 
152 		while (aux != NIL) {
153 			resultado++;
154 			aux = aux.getForward(0);
155 		}
156 
157 		return resultado;
158 	}
159 
160 	@Override
161 	public SkipListNode<T>[] toArray() {
162 		int size = size() + 2;
163 
164 		SkipListNode<T>[] array = new SkipListNode[size];
165 		SkipListNode<T> auxiliar = root;
166 
167 		for (int i = 0; i < size; i++) {
168 			array[i] = auxiliar;
169 			auxiliar = auxiliar.getForward(0);
170 		}
171 
172 		return array;
173 	}
174 }