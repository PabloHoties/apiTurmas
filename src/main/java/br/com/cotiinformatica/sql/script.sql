-- Cria tabela de alunos
create table aluno (
    id			UUID			primary key,
    nome		varchar(100) 	not null,
    matricula 	varchar(20) 	not null,
    cpf 		varchar(14) 		not null
);

-- Criar tabela de professores
create table professor (
    id			UUID			primary key,
    nome 		varchar(100) 	not null,
    telefone 	varchar(20) 	not null
);

-- Criar tabela de turmas
create table turma (
    id				UUID			primary key,
    nome 			varchar(25) 	not null,
    data_inicio 	varchar(10) 		not null,
    data_termino 	varchar(10) 		not null,
    id_professor 	UUID			references professor(id)
);

-- Criar tabela de matriculas
create table matricula (
    id					UUID			primary key,
    id_turma 			UUID			references turma(id),
    id_aluno 			UUID			references aluno(id),
    data_matricula 	varchar(10) not null
);