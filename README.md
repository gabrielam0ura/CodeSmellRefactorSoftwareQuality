# Projeto - CodeSmellRefactorSoftwareQuality

## Estrutura do Projeto

- ``src/``: Código-fonte do projeto.
- ``rules.xml``: Regras do PMD utilizadas para identificar code smells.


## Identificação de Code Smells

1. Neste projeto, você pode utilizar o PMD e o arquivo "rules.xml" para identificar os seguintes code smells:
   Data Class (DC), Long Method (LM) e Long Parameter List (LPL).
    ``
     pmd.bat check -f json -R .\regras.xml -d .\ -r ./All.json
    ``
2. Você pode utilizar o plugin IntelliJDeodorant para identificar o code smell Feature Envy (FE).
   É necessário utilizar o IntelliJ na versão 2021.2.4, pois essa é a última versão compatível com o plugin.


## Refactoring

O projeto possui 100 métodos de teste com um total de 228 asserções relacionadas ao comportamento dos code smells.
Ao refatorar o código com qualquer modelo de linguagem (LLM), é possível executar os testes para verificar se houve alguma alteração no resultado, uma vez que a refatoração modifica apenas a estrutura interna do código, sem alterar seu comportamento externo.

## Este projeto contém os seguintes code smells:


### 5 Data Classes
- Card, Reference, ToDo, Task, SearchLog

### 5 Long Method
- LeitnerSystem, KanbanView, ToDoTracker, StudyMaterial, StudyGoal

### 5 Feature Envy
- TimelineView, GeneralSearch, StudyCardsController, MaterialSearch, RegistrySearch

### 5 Long Parameter List
- AudioReference, HabitTracker, StudyObjective, StudyPlan, StudyTaskManager



