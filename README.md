# Организация простой файловой системы цепочки блоков
При использовании связного списка для хранения перечня
свободных блоков ФС очень часто возникает ситуация, когда
свободными оказываются несколько (иногда довольно много)
подряд расположенных блоков. В этом случае целесообразно
хранить информацию о них не в виде простого списка, а в виде
списка цепочек блоков, описывая каждую из них парой вида
«начальный блок»–«количество блоков в цепочке». Т.е.,
например, вместо последовательности списка
«34,35,36,37,38,39,682,683,684,560, …» записывать пары «(34, 6);
(682, 3); ...».
Такой подход может существенно сократить длину списка и
уменьшить частоту обращений ФС к носителю для записи/чтения
служебной информации.
