create table "accounts"
(
    "account_id" uuid not null constraint accounts_pk primary key,
    "des_name" varchar(255) not null,
    "des_document" varchar(255) not null constraint uq_accounts_document unique,
    "ind_document_type" varchar(255) not null,
    "num_balance" numeric(38,2) not null,
    "dat_created_at" timestamp(6) not null,
    "dat_updated_at" timestamp(6)
);

create index idx_accounts_document on "accounts"("des_document");
create index idx_accounts_document_type on "accounts"("ind_document_type");
