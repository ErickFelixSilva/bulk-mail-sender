import React, { useState, useEffect, useCallback } from 'react';
import { getNonprofits, saveNonprofit, updateNonprofit, deleteNonprofit, Nonprofit } from '../services/NonprofitService';
import { sendEmails } from '../services/EmailService';
import axios, { CancelToken, isCancel } from 'axios';
import { handleAxiosError } from '../utils/errorHandler';
import NonprofitItem from './NonprofitItem';

function NonprofitList(): JSX.Element {
	const [nonprofits, setNonprofits] = useState<Nonprofit[]>([]);
	const [selectedNonprofits, setSelectedNonprofits] = useState<number[]>([]);
	const [editingNonprofit, setEditingNonprofit] = useState<Nonprofit | null>(null);
	const [newNonprofit, setNewNonprofit] = useState<Nonprofit>({ name: '', email: '', address: '', emailSent: false });

	useEffect(() => {
		const source = axios.CancelToken.source();
		fetchNonprofits(source.token);

    return () => {
      source.cancel();
    };
	}, []);

	const fetchNonprofits = async (cancelToken?: CancelToken) => {
		try {
			const data = await getNonprofits({ cancelToken });
			setNonprofits(data);
		} catch (error: any) {
			if (isCancel(error)) {
        console.log('Request cancelled');
			} else {
				handleAxiosError('Failed to fetch nonprofits', error);
			}
		}
	};

	const handleSaveNonprofit = async () => {
		try {
			if (editingNonprofit) {
				await updateNonprofit(editingNonprofit);
				setEditingNonprofit(null);
			} else {
				await saveNonprofit(newNonprofit);
				setNewNonprofit({ name: '', email: '', address: '', emailSent: false });
			}
			fetchNonprofits();
		} catch (error: any) {
			handleAxiosError('Failed to save nonprofit', error);
		}
	};

	const handleDeleteNonprofit = useCallback( async (id: number) => {
		const confirmDelete = window.confirm('Are you sure you want to delete this nonprofit?');
		if (!confirmDelete) {
			return;
		}

		try {
			await deleteNonprofit(id);
			fetchNonprofits();
		} catch (error: any) {
			handleAxiosError('Failed to delete nonprofit', error);
		}
	}, []);

	const handleSelectNonprofit = useCallback((id: number) => {
		setSelectedNonprofits((prevSelected) =>
			prevSelected.includes(id) ? prevSelected.filter((nid) => nid !== id) : [...prevSelected, id]
		);
	}, []);

	const handleEditNonprofit = useCallback((nonprofit: Nonprofit) => {
		setEditingNonprofit(nonprofit);
	}, []);

	const handleSendEmail = async () => {
		try {
			await sendEmails(selectedNonprofits);
			await fetchNonprofits();
		} catch (error: any) {
			handleAxiosError('Failed to send email to selected nonprofits', error);
		}
	}

	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		if (editingNonprofit) {
			setEditingNonprofit({ ...editingNonprofit, [name]: value });
		} else {
			setNewNonprofit({ ...newNonprofit, [name]: value });
		}
	};

	return (
		<div className="container mx-auto p-4">
			<h1 className="text-blue-600 text-3xl font-bold mb-4">Nonprofits</h1>
			<div className="mb-4">
				<h2 className="text-xl font-bold mb-2">{editingNonprofit ? 'Edit Nonprofit' : 'Add New Nonprofit'}</h2>
				<div className="flex space-x-4">
					<input
						name="name"
						type="text"
						placeholder="Name"
						className="p-2 border border-gray-300 rounded-lg mb-2"
						value={editingNonprofit ? editingNonprofit.name : newNonprofit.name}
						onChange={handleChange}
					/>
					<input
						name="email"
						type="email"
						placeholder="Email"
						className="p-2 border border-gray-300 rounded-lg mb-2"
						value={editingNonprofit ? editingNonprofit.email : newNonprofit.email}
						onChange={handleChange}
					/>
					<input
						name="address"
						type="text"
						placeholder="Address"
						className="p-2 border border-gray-300 rounded-lg mb-2"
						value={editingNonprofit ? editingNonprofit.address : newNonprofit.address}
						onChange={handleChange}
					/>
					<button
						className="h-full bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700"
						onClick={handleSaveNonprofit}
					>
						{editingNonprofit ? 'Update' : 'Add'}
					</button>
					{editingNonprofit && (
						<button
							className="h-full bg-gray-600 text-white px-4 py-2 rounded-lg hover:bg-gray-700 ml-2"
							onClick={() => setEditingNonprofit(null)}
						>
							Cancel
						</button>
					)}
				</div>
			</div>
			<table className="min-w-full bg-white">
				<thead>
					<tr>
						<th className="py-2 px-4 text-left">Select</th>
						<th className="py-2 px-4 text-left">Name</th>
						<th className="py-2 px-4 text-left">Email</th>
						<th className="py-2 px-4 text-left">Address</th>
						<th className="py-2 px-4 text-left">Email Sent</th>
						<th className="py-2 px-4 text-left">Actions</th>
					</tr>
				</thead>
				<tbody>
					{nonprofits.map((nonprofit) => (
						<NonprofitItem key={nonprofit.id} nonprofit={nonprofit}
							selectedNonprofits={selectedNonprofits}
							handleSelectNonprofit={handleSelectNonprofit}
							handleEditNonprofit={handleEditNonprofit}
							handleDeleteNonprofit={handleDeleteNonprofit} />
					))}
				</tbody>
			</table>
			{nonprofits.length > 0 && (
				<div className="flex justify-end mt-4">
					<button disabled={selectedNonprofits.length === 0}
						className={`h-full px-4 py-2 rounded-lg ml-2 ${selectedNonprofits.length === 0 ?
							'bg-gray-600 text-white cursor-not-allowed' :
							'bg-blue-600 text-white hover:bg-blue-700'}`}
						onClick={handleSendEmail}>
						{selectedNonprofits.length === 0 ? 'Select a nonprofit to send email' :
							'Send email to selected nonprofits'}</button>
				</div>
			)}
		</div>
	);
};

export default NonprofitList;
