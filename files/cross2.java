private static void cross(int[][] Group) {
		for(int i=0;i<M;i++) {
			if(random.nextDouble() > CP) continue;
			int j = random.nextInt(M);
			while(i==j) j = random.nextInt(M);
			int[] posi = new int[n];
			int[] posj = new int[n];
			for(int k=1;k<n+1;k++) {
				posi[ Group[i][k] ] = k;
				posj[ Group[j][k] ] = k;
			}
			for(int a=1;a<n/2;a++) {
				int ai = Group[i][a];
				int aj = Group[j][a];
				Group[j][ posj[ai] ] = aj;
				Group[i][ posi[aj] ] = ai;
				Group[j][a] = ai;
				Group[i][a] = aj;
				int t = posi[aj];
				posi[aj] = posi[ai];
				posi[ai] = t;
				t = posj[aj];
				posj[aj] = posj[ai];
				posj[ai] = t;
			}
		}
	}
