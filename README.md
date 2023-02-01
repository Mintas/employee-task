# Employee Hierarchy

### How-to guide
For further reference, please consider the following sections:

* git clone project repo (e.g. https://github.com/Mintas/employee-task )
* run './gradlew letsRun -x test'  (there are integration tests that can take time)
* it will build project (skipping tests), pack image and start docker-compose
* docker attach employee-hierarchy-project_employee_1
* have fun

### Troubleshooting
In case of any problems with build and run contact me.

### Memory measurements
1. single employee row takes 24b appoximately in csv
2. that makes 10kb have ~415 rows and 10Gb ~415*10^6 = 415_000_000 rows (I wonder if any company can have this much employees)
3. estimate Employee class: (8 + 3*4 + 4 padding) + (16 + 16 + (24 + 104)) = 24 + 160 = 184b
4. estimate HierarchyNode: 24 + 24 + (#E * 4) = 48 + (4*#E)
5. Estimate Hierarchy (tree + index): 24 + (48 + 40*#E) = 72 + (40*#E)
6. total estimate: 48 + 72 + (184 + 4 + 40)* #E = 120 + 228*#E
7. that leads us to 264kb for 1155 rows; 95Gb for 10Gb csv; 
8. measurement with Idea MemoryAgent gives 234kb for 1155 employees, that makes calculations pretty accurate with respect to refsize effect of huge heap

### TODO List:
Ordered by implementation priority
1. add more javadocs
2. add more tests
3. improve readme
4. add static code analysis: pmd, checkstyle; add jacoco
