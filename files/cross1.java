	private static void cross(int a, int b) {
		int ranNum1 = random.nextInt(cityNum);
		int ranNum2 = random.nextInt(cityNum);
		while (ranNum1 == ranNum2) {
			ranNum1 = random.nextInt(cityNum);
			ranNum2 = random.nextInt(cityNum);
		}
		if (ranNum1 > ranNum2) {
			int t = ranNum1;
			ranNum1 = ranNum2;
			ranNum2 = t;
		}
		// Swap
		int t;
		for (int i = ranNum1; i <= ranNum2; i++) {
			t = sonPopulation[a][i];
			sonPopulation[a][i] = sonPopulation[b][i];
			sonPopulation[b][i] = t;
		}
		// Check and correct
		boolean[] hasUsed1 = new boolean[cityNum];
		boolean[] hasUsed2 = new boolean[cityNum];
		for (int i = 0; i < cityNum; i++) {
			hasUsed1[i] = false;
			hasUsed2[i] = false;
		}

		for (int i = 0; i < cityNum; i++) {
			if (hasUsed1[sonPopulation[a][i]]) {
				int j = 0;
				while (j < cityNum && hasUsed1[j]) {
					j++;
				}
				sonPopulation[a][i] = j;
			}
			if (hasUsed2[sonPopulation[b][i]]) {
				int j = 0;
				while (j < cityNum && hasUsed2[j]) {
					j++;
				}
				sonPopulation[b][i] = j;
			}

			hasUsed1[sonPopulation[a][i]] = true;
			hasUsed2[sonPopulation[b][i]] = true;

		}
//        System.out.println("cross:");
//        for(int i=0;i<cityNum;i++) {
//			System.out.print(sonPopulation[a][i]+" ");
//		}
//        System.out.println();
//        for(int i=0;i<cityNum;i++) {
//			System.out.print(sonPopulation[b][i]+" ");
//		}
//        System.out.println();
	}