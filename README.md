
# Второе домашнее задани по курсу Конструирование программного обеспечения 2022.
## Фирсов Федор БПИ219 
### Задача
Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также
другие папки. В других папках также могут находиться текстовые файлы и папки.

В каждом файле может быть ни одной, одна или несколько директив формата:

require ‘<путь к другому файлу от корневого каталога>’

Директива означает, что текущий файл зависит от другого указанного файла.

Необходимо выявить все зависимости между файлами, построить сортированный
список, для которого выполняется условие: если файл А, зависит от файла В, то файл
А находится ниже файла В в списке.

### Решение
Для решения этой задачи я написал следующие классы:
```
1) Graph - Граф, который который хранит и строит зависимости между объктами. Проверяет граф на наличие циклической зафисимости.
2) Node - Узел графа. Хранит значение вершины и индексы детей.
3) FilesReader - Получение входных данный, получение первичной информкции о файлах и передача из в Graph.
```
Также добавлены проверки на то, что корневой котолог существует, файлы возможны к открытию и чтению, в файлах содержится то, что нужно.

Надеюсь, это дастаточно подробное описание и разбиение на коммиты осмысленное) 

### Формат ввода
```
Одна строка - путь к корневой папке.
```
### Формат вывода
Список выводится в следующем виде:
```
путь_к_файлу_1:
содержание_файла
путь_к_файлу_2:
содержание файла 
может занимать 
несколько строк
```
