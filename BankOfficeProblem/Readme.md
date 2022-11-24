# Bank Office Problem


## Question

A bank office keeps record of its customers in an array A[1...n] sorted by account numbers. However, a bank officer accidentally shifts the array elements k < n positions to the right in a circular-fashion. Note that k is not necessarily constants (e.g. might be a function of n). For example, let A = [9, 12, 17, 21, 33 41]. When shifted k = 2 positions to the right, A becomes [33, 41, 9, 12, 17, m21 However, if k = 4, A becomes [17, 21, 33, 41, 9, 12]. The officer realizes the problem and wants to fix it by sorting the shifted version of the array with Insertion sort. Find the complexity of the Insertion sort for such an input scenario. Generate random arrays of size n = 216 and shift the array for k = 1, n 16, 2n/16, 3n/16, 4n/16, 5n/16, 6n/16, 7n/16, 8n/16, 9n/16, 10n/16, 11n/16, 12n/16 13n/16, 14n/16, 15n/16, n − 1 positions. For each k, measure the actual running times to sort the shifted array and organize the calculated times into a table Discuss the results and compare the behaviors of the actual running times and the theoretical complexity
